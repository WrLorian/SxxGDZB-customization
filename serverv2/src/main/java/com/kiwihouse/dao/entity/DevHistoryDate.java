package com.kiwihouse.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class DevHistoryDate {
    private Integer id;

    private String dataJson;

    private String imei;

    private String addTime;

    private String type;

    private Integer eqptType;
    
    private Float cur;
    private Float curA;
    private Float curB;
    private Float curC;
    private Float vol;
    private Float volA;
    private Float volB;
    private Float volC;
    private Float pwr;
    private Float pwrA;
    private Float pwrB;
    private Float pwrC;
    private Float kwh;
    
    private Float lineTemp;
    
    private Float leakCur;
    
    private Integer csq;
    
    private Float pwrFct;
    private Float pwrFctA;
    private Float pwrFctB;
    private Float pwrFctC;
   
    
}