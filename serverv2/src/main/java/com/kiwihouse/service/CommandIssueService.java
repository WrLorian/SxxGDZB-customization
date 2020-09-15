package com.kiwihouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.common.bean.ApiKeys;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.EqptTypeSta;
import com.kiwihouse.common.bean.OneNtCode;
import com.kiwihouse.common.bean.OneNtUrl;
import com.kiwihouse.common.utils.HttpClientUtil;
import com.kiwihouse.common.utils.ReflectUtil;
import com.kiwihouse.dao.mapper.EquipmentMapper;
import com.kiwihouse.dto.OneNetDataDetailDto;
import com.kiwihouse.dto.OneNetDataDto;
import com.kiwihouse.dto.RegisterDevice;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.CommandVo;
import com.kiwihouse.vo.onenet.DeviceQuery;
import com.kiwihouse.vo.onenet.DeviceReg;
import com.kiwihouse.vo.onenet.OneNetResult;

/**
 * @author yjzn
 * @date 2020-01-03-上午 11:23
 */
@Service
public class CommandIssueService {
    private final Logger logger = LoggerFactory.getLogger(CommandIssueService.class);

    @Autowired
    EquipmentMapper equipmentMapper;
    @Autowired
    CheckAdminService checkAdminService;

    /**
     * 多个设备批量命令下发
     *
     * @param list
     * @param request
     * @return
     */
    public ResultList commandsIssued(List<CommandVo> list, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
//        String adminId = request.getHeader("dz-usr");
        list.forEach(commandVo -> {

            String imei = commandVo.getImei();
//            if(checkAdminService.isEqptBelong2admin(imei, adminId)) {
//                HashMap<String, Integer> hashMap = ReflectUtil.GetNoneEmptyFieldMap(commandVo.getRegister());
//                String response = commandsIssued(hashMap, imei, commandVo.getEqptType());
//                map.put(imei, response);
//            }else{
//                map.put(imei, "对该设备没有命令下发权限");
//            }
            HashMap<String, Integer> hashMap = ReflectUtil.GetNoneEmptyFieldMap(commandVo.getRegister());
            String response = commandsIssued(hashMap, imei, commandVo.getEqptType());
            map.put(imei, response);
        });

        return new ResultList(Code.COMMAND_ISSUED.getCode(), Code.COMMAND_ISSUED.getMsg(), new Result<>(0, map));
    }

    /**
     * 将map中的指令集发送到onenet平台
     *
     * @param map      指令集
     * @param imei
     * @param eqptType
     * @return
     */
    public String commandsIssued(HashMap<String, Integer> map, String imei, String eqptType) {

        String url = OneNtUrl.writeResource + imei;

        //data对象
        OneNetDataDetailDto obj = new OneNetDataDetailDto();

        obj.setRes_id(5750);
        //将封装好的参数放到val中
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
        System.out.println("-------------->" + jsonObject.toJSONString());
        obj.setVal(jsonObject.toJSONString());

        //将data对象放到list中
        List<OneNetDataDetailDto> list = new ArrayList<>();
        list.add(obj);

        //将list放到data中
        OneNetDataDto oneNetDataDto = new OneNetDataDto();
        oneNetDataDto.setData(list);

        //先转成JSON对象
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(oneNetDataDto);

        String response = "";

        if (EqptTypeSta.YG.equals(eqptType)) {
            response = HttpClientUtil.doPost(url, jsonObject1.toJSONString(), ApiKeys.YGKey);
        } else if (EqptTypeSta.DX.equals(eqptType)) {
            response = HttpClientUtil.doPost(url, jsonObject1.toJSONString(), ApiKeys.YDAQKey);
        }else if (EqptTypeSta.SX.equals(eqptType)) {
            response = HttpClientUtil.doPost(url, jsonObject1.toJSONString(), ApiKeys.GLJCKey);
        }
        return response;
    }


    /**
     * 将设备注册到onenet平台
     *
     * @param imei   IMEI号
     * @param imsi   IMSI号
     * @param eqptSn 设备序列号
     * @return 是否注册成功
     */
    public OneNetResult registerToOnenet(String eqptId, String imei, String imsi, String eqptSn, String eqptType, String adminId) {

        if (checkAdminService.isEqptBelong2admin(eqptId, adminId)) {
            //nb设备协议必须是LWM2M
            final String PROTOCOL = "LWM2M";
            String resultString = "";

            HashMap<String, String> auth_info = new HashMap<>();
            auth_info.put(imei, imsi);
            RegisterDevice registerDevice = new RegisterDevice(eqptSn, PROTOCOL, auth_info, "true");

            String jsonString = JSON.toJSONString(registerDevice);
            logger.info("设备注册到onenet>> {}", jsonString);
            if (EqptTypeSta.YG.equals(eqptType)) {
                resultString = HttpClientUtil.doPost(OneNtUrl.REGISTER, jsonString, ApiKeys.YGKey);
            } else if (EqptTypeSta.DX.equals(eqptType)) {
                resultString = HttpClientUtil.doPost(OneNtUrl.REGISTER, jsonString, ApiKeys.YDAQKey);
            } else if (EqptTypeSta.SX.equals(eqptType)) {
                resultString = HttpClientUtil.doPost(OneNtUrl.REGISTER, jsonString, ApiKeys.GLJCKey);
            }

            logger.info("onenet平台返回<< {}", resultString);
            OneNetResult oneNetResult = JSONObject.parseObject(resultString, OneNetResult.class);
            String errno = oneNetResult.getErrno();

            if (OneNtCode.SUCC.equals(errno) && null != eqptId) {
                //将onenet返回的device_id保存到数据库
                String data = oneNetResult.getData();
                DeviceReg deviceReg = JSONObject.parseObject(data, DeviceReg.class);
                equipmentMapper.addDeviceId(eqptId, deviceReg.getDevice_id());
            }
            return oneNetResult;
        } else {
            return new OneNetResult()
                    .setErrno(String.valueOf(Code.PRIVILEGE_FAIL.getCode()))
                    .setError("对设备sn为" + eqptSn + "的设备没有操作权限")
                    .setData(null);
        }
    }

    /**
     * 设备批量注册到onenet平台
     *
     * @param list 设备信息集合
     * @return 注册失败设备的SN号即错误原因
     */
    public ResultList registerToOnenetList(List<com.kiwihouse.vo.kiwihouse.RegisterDevice> list, HttpServletRequest request) {

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        for (com.kiwihouse.vo.kiwihouse.RegisterDevice rgs : list) {
            OneNetResult oneNetResult = registerToOnenet(rgs.getEqptId(), rgs.getImei(), rgs.getImsi(), rgs.getEqptSn(), rgs.getEqptType(), request.getHeader("dz-usr"));
            String errno = oneNetResult.getErrno();
            if (OneNtCode.INVALID_AUTH_INFO.equals(errno)) {      //注册是imei和imsi不正确
                map.put(rgs.getEqptSn(), OneNtCode.INVALID_AUTH_INFO_MSG);
                arrayList.add(map);
            } else if (String.valueOf(Code.PRIVILEGE_FAIL.getCode()).equals(errno)) {      //管理员对该设备没有权限
                map.put(rgs.getEqptSn(), oneNetResult.getError());
                arrayList.add(map);
            }
        }
        return new ResultList(Code.COMMAND_ISSUED.getCode(), Code.COMMAND_ISSUED.getMsg(), new Result<>(0, arrayList));

    }


    /**
     * 根据deviceId查询设备信息，目前只查询了设备在线状态，以下是可以查询的全部信息:
     * <p>
     * {
     * "errno": 0,
     * "data": {
     * "area": "3",
     * "private": false,
     * "create_time": "2020-01-05 11:11:45",
     * "act_time": "2020-01-05 11:12:53",
     * "obsv": true,
     * "ack": true,
     * "auth_info": {
     * "869060039714583": "460046511704806"
     * },
     * "last_ct": "2020-01-08 16:01:40",
     * "imsi": "460046511704806",
     * "title": "device002",
     * "tags": [],
     * "obsv_st": false,
     * "protocol": "LWM2M",
     * "rg_id": "869060039714583",
     * "imsi_old": [
     * "460046511704806",
     * "460045500202638"
     * ],
     * "imsi_mt": "2020-01-06 20:16:55",
     * "online": false,
     * "location": {
     * "lat": 0,
     * "lon": 0
     * },
     * "id": "580216995",
     * "datastreams": [
     * {
     * "create_time": "2020-01-05 11:13:00",
     * "uuid": "b4a2416a-d3a3-4458-83d9-38b92d87bc92",
     * "id": "3300_0_5751"
     * },
     * {
     * "create_time": "2020-01-05 11:14:38",
     * "uuid": "99044a72-00ea-49f4-9f31-b4dc3b88b5a7",
     * "id": "3300_0_5750"
     * }
     * ],
     * "desc": ""
     * },
     * "error": "succ"
     * }
     *
     * @param deviceId onenet平台生成设备ID
     * @return OneNetResult.toString，目前之后code(自定义状态码)和online
     */
    public DeviceQuery queryDeviceSta(String deviceId, String eqptType) {
        String resultString = "";

        if (StringUtils.isBlank(deviceId)) {
            return new DeviceQuery()
                    .setOnline("false")
                    .setCode(Code.DEVICEID_INEXISTENCE.getCode());
        }
        //查询设备状态URL
        String url = OneNtUrl.querySta + deviceId;
        if (EqptTypeSta.DX.equals(eqptType)) {
            resultString = HttpClientUtil.doGet(url, ApiKeys.YDAQKey);
        } else if (EqptTypeSta.YG.equals(eqptType)) {
            resultString = HttpClientUtil.doGet(url, ApiKeys.YGKey);
        } else if (EqptTypeSta.SX.equals(eqptType)) {
            resultString = HttpClientUtil.doGet(url, ApiKeys.GLJCKey);
        } else {
            throw new RuntimeException("设备deviceId为: " + deviceId + " 的设备类型不正确,其设备类型为: " + eqptType);
        }
        OneNetResult oneNetResult = JSONObject.parseObject(resultString, OneNetResult.class);
        String errno = oneNetResult.getErrno();
        boolean equals = "0".equals(errno);
        if (equals) {
            String data = oneNetResult.getData();
            DeviceQuery deviceQuery = JSONObject.parseObject(data, DeviceQuery.class);
            String online = deviceQuery.getOnline();
            if ("true".equals(online)) {
                deviceQuery.setCode(Code.ONLINE.getCode());
            } else {
                deviceQuery.setCode(Code.NOTONLINE.getCode());
            }
            return deviceQuery;
        } else if ("3".equals(errno)) {
            return new DeviceQuery()
                    .setOnline("false")
                    .setCode(Code.DEVICEID_INEXISTENCE.getCode());
        } else {
            return new DeviceQuery()
                    .setOnline("false")
                    .setCode(Code.DEVICEID_INEXISTENCE.getCode());
        }
    }
}
