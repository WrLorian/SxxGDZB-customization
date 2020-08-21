package com.kiwihouse.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @date 2019年9月12日10:20:21
 * @author yjzn
 */
public class FileThread {
    /**
     * 读取地区编号文件，按照":"切分,然后保存到map中返回
     * key:区域编号 value:地区名称
     * @param filepath 文件路径
     * @return 保存区域编码和地区名称的map
     * @throws Exception
     */
    public ConcurrentHashMap<String, String> getCodeMap(String filepath) throws Exception{

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        String line;

        //读取文件
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filepath);
        InputStreamReader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        /*
            逐行操作，按照":"切分字符串，分别存入一个map中
         */
        while (null != (line = br.readLine())) {
            String[] split = line.split(":");
            map.put(split[0], split[1]);
        }

        in.close();
        br.close();
        return map;
    }

    /**
     * 根据code查询这个code下一级的所有区域
     * @param filepath
     * @param code
     * @return
     * @throws Exception
     */
    public ConcurrentHashMap<String, String> getCodeMap(String filepath,String code) throws Exception{

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        String line;

        //读取文件
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filepath);
        InputStreamReader in = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        /*
            逐行操作，按照":"切分字符串，分别存入一个map中
         */
        while (null != (line = br.readLine())) {
            String[] split = line.split(":");
            if(split[0].startsWith(code)) {
                map.put(split[0], split[1]);
            }
        }

        in.close();
        br.close();
        return map;
    }

}