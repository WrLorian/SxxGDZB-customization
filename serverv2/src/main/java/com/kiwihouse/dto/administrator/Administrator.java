package com.kiwihouse.dto.administrator;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xin
 * @date 2020/5/28
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@ApiModel("管理员实体")
public class Administrator {
    //Id
    private int adminId;
    //名称
    private String adminName;
    //密码
    private String password;
    //手机
    private String phone;
    //管理员级别
    private int rank;
    //上级管理员ID
    private int parentId;
    //入库时间
    private String addTime;
    //群组IDs
    private String groupIds;

    public String[] getGroupIdsList() {
        return this.groupIds.split("-");
    }

}
