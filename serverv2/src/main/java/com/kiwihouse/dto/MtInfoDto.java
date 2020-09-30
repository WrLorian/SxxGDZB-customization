package com.kiwihouse.dto;
import java.util.HashMap;
import com.kiwihouse.util.excel.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-4 11:43:27
 */
@ToString
@Getter
@Setter
@ApiModel(description = "维修记录参数")
public class MtInfoDto {
	
	
	
	@Excel(name = "维修ID")
    @ApiModelProperty(value = "维修工单ID",name = "mtId")
    private String mtId;
	
	@Excel(name = "告警ID")
    @ApiModelProperty(value = "告警ID",name = "alarmId")
    private Integer alarmId;
	
	@Excel(name = "设备序imei")
    @ApiModelProperty(value = "设备imei",name = "imei")
    private String imei;
	
	@Excel(name = "维修记录")
    @ApiModelProperty(value = "维修信息",name = "msg")
    private String mtMsg;
	
	@Excel(name = "告警信息",width = 60)
    @ApiModelProperty(value = "告警信息",name = "alarmMsg")
    private String alarmMsg;
	
	@Excel(name = "维修状态",readConverterExp = "0=未维修,1=已维修,2=已撤回")
    @ApiModelProperty(value = "维修状态",name = "mtStatus")
    private String mtStatus;
	
	@Excel(name = "维修人员名称")
    @ApiModelProperty(value = "维修人员姓名",name = "mtName")
    private String mtName;
    
    @Excel(name = "维修人员电话")
    @ApiModelProperty(value = "维修人员电话",name = "mtPhone")
    private String mtPhone;

	@Excel(name = "维修时间",width = 30)
    @ApiModelProperty(value = "维修时间",name = "updateTime")
    private String updateTime;
	
    @ApiModelProperty(value = "设备名称",name = "eqptName")
    private String eqptName;
	
    @ApiModelProperty(value = "设备类型",name = "eqptType")
    private String eqptType;

    @ApiModelProperty(value = "地址ID",name = "siteId")
    private String siteId;

    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    
	
    
    @ApiModelProperty(value = "告警信息map",name = "almValue")
    private HashMap<String,Object> almValue;

    @ApiModelProperty(value = "告警时间",name = "addTime")
    private String addTime;

    @ApiModelProperty(value = "告警类型",name = "alarmType")
    private String alarmType;
    
    @ApiModelProperty(value = "省",name = "province")
    private String province;

    @ApiModelProperty(value = "市",name = "city")
    private String city;

    @ApiModelProperty(value = "区",name = "district")
    private String district;

    @ApiModelProperty(value = "地址",name = "address")
    private String address;

    @ApiModelProperty(value = "管理员ID",name = "adminId")
    private String adminId;

    @ApiModelProperty(value = "维修工单类型(1:用电设备，2:烟感设备)",name = "mtType")
    private String mtType;
    
}
