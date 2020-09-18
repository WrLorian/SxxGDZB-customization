package com.kiwihouse.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.WWTL;
import com.kiwihouse.common.httpclient.DefaultHttpClient;
import com.kiwihouse.common.httpclient.HttpException;
import com.kiwihouse.common.httpclient.HttpMethod;
import com.kiwihouse.common.httpclient.HttpRequest;
import com.kiwihouse.common.httpclient.HttpResponse;
import com.kiwihouse.common.httpclient.IHttpClient;
import com.kiwihouse.common.httpclient.SendSmsResult;
import com.kiwihouse.common.httpclient.SmsBase;
import com.kiwihouse.common.utils.SmsSenderUtil;
import com.kiwihouse.dao.mapper.AuthUserMapper;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.SendSmsVo;


/**
 * @author yjzn
 * @date 2019-12-12-下午 5:06
 */
@Service
public class SendSmsService extends SmsBase {
    private final Logger logger = LoggerFactory.getLogger(SendSmsService.class);

    @Autowired
    AuthUserMapper authUserMapper;
    // Accept
    private static final String JSON = "application/json";

    /**
     * 客户端和服务进行数据交互的超时时间 10s，10s内无数据包交互
     */
    private static final int SOCKET_TIMEOUT = 60 * 1000;
    /**
     * 建立连接超时时间
     */
    private static final int CONNECT_TIMEOUT = 60 * 1000;
    /**
     * 获取连接最长时间
     */
    private static final int CONNECT_REQUEST_TIMEOUT = 500;

    public SendSmsService()  {
        super(WWTL.accountId, WWTL.password,new DefaultHttpClient());
    }

    public SendSmsService(String account, String password) {
        super(account, password, new DefaultHttpClient());
    }

    public SendSmsService(String accountId, String password, IHttpClient httpClient) {
        super(accountId, password, httpClient);
    }

    /**
     * 处理内容，转换为微网通联接口需要的格式
     * @param list 发送内容
     * @return
     */
    public ResultList SendSms(List<SendSmsVo> list) {

        final SendSmsResult[] sendSmsResult = new SendSmsResult[2];
        list.forEach(sendSmsVo -> {
            String sms = "【电力监测平台】尊敬的用户,您的监测设备产生【"+sendSmsVo.getAlmMsg()+"】，为避免安全事故，请您及时检查并处理！告警详情：http://106.53.1.5:8701";

            logger.info("调用WWTL短信接口,SMS:{}",sms);
            String userPhone = sendSmsVo.getPhone();
            System.out.println(userPhone);
            sendSmsResult[0] = doPost(userPhone, sms);
            List<String> ctsPhones = authUserMapper.queryCtsPhone(userPhone);
            ctsPhones.forEach(phone->{
                sendSmsResult[1] = doPost(phone,sms);
                logger.info("向联系人发短信，返回值为:{}",sendSmsResult[1]);
            });
            logger.info("向用户发短信，返回值为:{}",sendSmsResult[0]);
        });

        HashMap<String,Object> map = new HashMap<>();
        if("succ".equals(sendSmsResult[0].Result)){
            map.put("msgId",sendSmsResult[0].MsgId);
            map.put("splitCount",sendSmsResult[0].SplitCount);
            return new ResultList(Code.SENDSMS_SUCC.getCode(),sendSmsResult[0].Reason,new Result<>(2,map));
        }else{
            return new ResultList(Code.SENDSMS_FAIL.getCode(),sendSmsResult[0].Reason,null);
        }
    }

    /**
     * httpclient 发送post请求
     * @param phone 电话号码
     * @param content 发送内容
     * @return
     */
    public SendSmsResult doPost(String phone, String content) {

        long random = SmsSenderUtil.getRandom();
        long timestamp = SmsSenderUtil.getCurrentTime();

        JSONObject body = new JSONObject();
        body.put("AccountId", WWTL.accountId);
        body.put("ProductId", WWTL.productId);
        body.put("PhoneNos", phone);
        body.put("Content", content);
        body.put("AccessKey", SmsSenderUtil.calcSendSmsSign(WWTL.accountId, phone, WWTL.password, random, timestamp));
        body.put("Timestamp", timestamp);
        body.put("Random", random);
        body.put("OutId", "");
        body.put("SendTime", "");
        body.put("ExtendNo", "");

        HttpRequest request = new HttpRequest(HttpMethod.POST, WWTL.url)
                .addHeader("Content-Type", "application/json")
//                .addQueryParameter("Account", this.accountId)	//将登陆账号放入QueryParameter中
//                .addQueryParameter("random", random)	//将一个随机数放入QueryParameter中
                .setConnectTimeout(60 * 1000)
                .setRequestTimeout(60 * 1000)
                .setBody(body.toString());

        HttpResponse response = null;
        try {
            response = httpClient.fetch(request);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            handleError(response);
        } catch (HttpException e) {
            return new SendSmsResult().parseFromHttpResponse(response);
        }

        return new SendSmsResult().parseFromHttpResponse(response);
//        System.out.println(phone+":"+body.toString());
//        return "true";
    }
}
