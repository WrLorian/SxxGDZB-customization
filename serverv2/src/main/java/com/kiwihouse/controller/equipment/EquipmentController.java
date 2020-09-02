package com.kiwihouse.controller.equipment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.UserInfo;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.dao.mapper.AuthUserMapper;
import com.kiwihouse.dto.Eqpt4UpdateDto;
import com.kiwihouse.dto.EqptInfoDto;
import com.kiwihouse.service.CheckAdminService;
import com.kiwihouse.service.EquipmentService;
import com.kiwihouse.vo.entire.Log;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.EqptAddVo;
import com.kiwihouse.vo.kiwihouse.EqptQueryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 设备信息接口
 *
 * @author yjzn
 * @date 2019-12-30 10:26:27
 */
@RestController
@RequestMapping("/equipment")
@Api(tags = "设备信息接口")
public class EquipmentController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    EquipmentService equipmentService;
    @Autowired
    CheckAdminService checkAdminService;
    @Autowired
    AuthUserMapper authUserMapper;
    @ApiOperation(value = "queryInfo",
            notes = "<br>@description: <b>查询设备信息</b></br>" +
                    "<br>@Return: <b>以区为单位进行区分</b></br>" +
                    "<br>@Date: <b>2019-12-30 10:33:36</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "回调参数", response = EqptInfoDto.class)})
    @GetMapping("/info")
    public Map<String, Object> queryInfo(@Validated EqptQueryVo eqptQueryVo, HttpServletRequest request) {
        checkAdminService.verifyAdminId(request.getHeader("dz-usr"), eqptQueryVo);
        AuthUser authUser  = authUserMapper.selectByPrimaryKey(Integer.valueOf(request.getHeader("dz-usr")));
        return equipmentService.queryInfo(eqptQueryVo, authUser);
    }

    @ApiOperation(value = "updateInfo",
            notes = "<br>@description: <b>修改设备信息</b></br>" +
                    "<br>@Date: <b>2020-1-3 17:25:42</b></br>",
            httpMethod = "PUT")
    @ApiResponses(@ApiResponse(code = 0, message = "回调参数：只有code和msg,无具体数据result"))
    @PutMapping("/info")
    public ResultList updateInfo(@RequestBody @Validated Eqpt4UpdateDto updateDto, HttpServletRequest request) {
        logger.info("更新设备信息>> {}", new Log().setIp(request.getRemoteAddr()).setMsg("更新设备信息").setParam(updateDto.toString()));
        String adminId = request.getHeader("dz-usr");
        if (!checkAdminService.isEqptBelong2admin(updateDto.getEqptId(), adminId)) {
            return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "对设备Id为`" + updateDto.getEqptId() + "`的设备没有修改权限", null);
        }
        updateDto.setDoAdminId(adminId);
        return equipmentService.updateInfo(updateDto);
    }

    @ApiOperation(value = "addInfo",
            notes = "<br>@description: <b>添加设备信息</b></br>" +
                    "<br>@Date: <b>2020-1-3 17:25:42</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0, message = "回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/info")
    public ResultList addInfo(@RequestBody @Validated EqptAddVo eqptAddVo, HttpServletRequest request) {
        logger.info("录入设备信息>> {}", new Log().setIp(request.getRemoteAddr()).setMsg("添加设备信息").setParam(eqptAddVo.toString()));
        return equipmentService.addInfo(eqptAddVo);
    }

    @ApiOperation(value = "deleteInfo",
            notes = "<br>@description: <b>删除设备信息</b></br>" +
                    "<br>@Date: <b>2020-1-3 17:25:42</b></br>",
            httpMethod = "DELETE")
    @ApiResponses(@ApiResponse(code = 0, message = "回调参数：只有code和msg,无具体数据result"))
    @ApiImplicitParam(paramType = "path", name = "eqptSn", dataType = "String", required = true, value = "设备序列号")
    @DeleteMapping("/info/{eqptSn}")
    public ResultList deleteInfo(@PathVariable String eqptSn, HttpServletRequest request) {
        logger.info("删除设备信息>> {}", new Log().setIp(request.getRemoteAddr()).setMsg("删除设备信息").setParam(eqptSn));
        if (!checkAdminService.isEqptSnBelong2admin(eqptSn, request.getHeader("dz-usr"))) {
            return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "对设备sn为`" + eqptSn + "`的设备没有删除权限", null);
        }
        return equipmentService.deleteInfo(eqptSn, new UserInfo(request));
    }


}