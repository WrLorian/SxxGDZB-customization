package com.kiwihouse.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;

/**
 * 烟感api-key:mTLkOxxyZ=q=7zsWIlxE=vQgfFw=
 * 火警api-key:z58=Euzrb0Kk0sz1bOwY55fIaWQ=
 * onenet平台写设备资源
 * @author yjzn
 * @date 2020-1-3 09:40:14
 */
public class HttpClientUtil {

    // content-type
    private static final String JSON = "application/json";

    /**
     * 客户端和服务进行数据交互的超时时间 10s，10s内无数据包交互
     */
    private static final int SOCKET_TIMEOUT = 10 * 1000;
    /**
     * 建立连接超时时间
     */
    private static final int CONNECT_TIMEOUT = 100 * 1000;
    /**
     * 获取连接最长时间
     */
    private static final int CONNECT_REQUEST_TIMEOUT = 500;

    /**
     * httpclient 发送post请求
     * @return
     */
    public static String doPost(String URL, String jsonString,String api_key) {

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //从连接池获取连接
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("api-key", api_key);
        httpPost.setHeader("Content-Type", JSON);

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)   //创建连接最长时间，单位毫秒
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)  //设置获取连接的最长时间，单位毫秒
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        httpPost.setConfig(config);

        try {
            httpPost.setEntity(new StringEntity(jsonString));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        CloseableHttpResponse response = null;
        String responseStr;
        try {
            response = httpClient.execute(httpPost);
            if(null!=response){
                responseStr = EntityUtils.toString(response.getEntity(), "utf8");
            }else {
                return "response is null";
            }

        }catch (SocketTimeoutException e) {
            return "read time out" ;
        } catch(IOException e){
            return "fail";
        }finally{
            if(null!=response){
                try{
                    response.close();
                }catch(IOException e){
                    return "fail";
                }
            }
        }
        return responseStr;
    }

    public static String doGet(String URL,String api_key) {

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //从连接池获取连接
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet(URL);
        httpGet.setHeader("api-key", api_key);

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)   //创建连接最长时间，单位毫秒
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)  //设置获取连接的最长时间，单位毫秒
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        String responseStr;
        try {
            response = httpClient.execute(httpGet);
            if(null!=response){
                responseStr = EntityUtils.toString(response.getEntity(), "utf8");
            }else {
                return "response is null";
            }

        }catch (SocketTimeoutException e) {
            return "read time out" ;
        } catch(IOException e){
            return "fail";
        }finally{
            if(null!=response){
                try{
                    response.close();
                }catch(IOException e){
                    return "fail";
                }
            }
        }
        return responseStr;
    }
}
