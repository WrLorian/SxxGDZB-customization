package com.kiwihouse.common.utils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjzn
 * @date 2019-10-18-下午 6:30
 */
public class GroupList {

    /**
     * 将list分组，每组有limit条(用于在代码中处理分页)
     * @param list List
     * @param limit 每组数据条数
     * @return map 每一Kye中有limit条数据的List
     */
    public static HashMap<Integer,List> groupList(List list,int limit){

        int listSize=list.size();
        HashMap<Integer,List> map = new HashMap<>();     //用map存起来新的分组后数据
        int keyToken = 0;
        for(int i = 0;i<list.size();i+=limit){
            if(i+limit>listSize){        //作用为toIndex最后没有limit条数据则剩余几条newList中就装几条
                limit=listSize-i;
            }
            List newList = list.subList(i,i+limit);
            map.put(keyToken, newList);
            keyToken++;
        }
        return map;
    }

}
