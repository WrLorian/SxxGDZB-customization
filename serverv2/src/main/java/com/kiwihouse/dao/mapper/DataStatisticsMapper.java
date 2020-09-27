package com.kiwihouse.dao.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dao.entity.DateStatis;
import com.kiwihouse.dto.EqptWarn;
import com.kiwihouse.dto.PerDayUsers;
import com.kiwihouse.vo.kiwihouse.DataStatisticsVo;

/**
 * @author yjzn
 * @date 2020-02-24-下午 5:02
 */
@Repository
public interface DataStatisticsMapper {

    List<EqptWarn> queryPreDayFireEqpt(DataStatisticsVo dataStatisticsVo);
    List<EqptWarn> queryUnderVol(DataStatisticsVo dataStatisticsVo);
    List<EqptWarn> queryOverVol(DataStatisticsVo dataStatisticsVo);
    List<EqptWarn> queryCur(DataStatisticsVo dataStatisticsVo);
    List<EqptWarn> queryOverload(DataStatisticsVo dataStatisticsVo);
    List<EqptWarn> queryLeak(DataStatisticsVo dataStatisticsVo);
    List<EqptWarn> queryTemp(DataStatisticsVo dataStatisticsVo);

    int queryEqptNum(HashMap<String,String> map);   //查询设备数量
    int queryFireAlmEqptNum(HashMap<String,String> map);    //查询火警告警设备数量
    int querySmokeAlmEqptNum(HashMap<String, String> map);  //查询烟雾告警设备数量
    List<EqptWarn> querySmokeAlmNums(HashMap<String, String> map);     //查询烟雾告警设备数量

    int queryAllNewUsers(HashMap<String, String> map);  //该段时间总新增用户
    List<PerDayUsers> queryPerDayNewUser(HashMap<String, String> map);    //查询每天新增用户数
    @Select("select count(*) from user where date_format(add_time,'%Y-%m-%d')=#{currentDate}")
    int queryNewUser(String currentDate);    //查询每天新增用户数

    @Select("select count(*) from device.maintain_info where mt_status = #{status}")
    int fireOrderNum(int status);
    @Select("select count(*) from device.maintain_smoke where mt_status = #{status}")
    int smokeOrderNum(int status);

    @Select("select count(*) from device.maintain_info where mt_status = #{status} and date_format(update_time,'%Y-%m-%d')=#{currentDate}")
    int fireCurrDayOrderNum(int status,String currentDate);
    @Select("select count(*) from device.maintain_smoke where mt_status = #{status} and date_format(update_time,'%Y-%m-%d')=#{currentDate}")
    int smokeCurrDayOrderNum(int status,String currentDate);
    /**
     * 查询告警总记录
     * @param dataStatisticsVo
     * @return
     */
    DateStatis queryDataStatois(DataStatisticsVo dataStatisticsVo);

}
