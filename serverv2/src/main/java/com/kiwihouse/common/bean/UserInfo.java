package com.kiwihouse.common.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kiwihouse.common.session.SessionManger;

import lombok.Data;

/**
 * @author xin
 * @date 2020/6/28
 */
@Data
public class UserInfo {
    /**
     * 用户类型
     */
    private String userType;

    private boolean admin;

    private String dzUsr;

    private String phone;

    private String userId;

    private String parentId;

    private String username;

    private HttpSession session;

    private String[] groupIds;


    public UserInfo(HttpServletRequest request) {
        String sessionId = request.getHeader("X-Token");
        if (!StringUtils.isBlank(sessionId)) {
            session = SessionManger.getInstance().getSession(sessionId);
        } else {
            session = request.getSession();
        }
        username = session != null ? String.valueOf(session.getAttribute("username")) : "";
        userId = session != null ? String.valueOf(session.getAttribute("userId")) : "";
        userType = session != null ? String.valueOf(session.getAttribute("userType")) : "";
        dzUsr = session != null ? String.valueOf(session.getAttribute("dzUsr")) : "";
        phone = session != null ? String.valueOf(session.getAttribute("phone")) : "";
        parentId = session != null ? String.valueOf(session.getAttribute("parentId")) : "";
        String groupIdArr = session != null ? String.valueOf(session.getAttribute("groupIds")) : "";
        if (!StringUtils.isBlank(groupIdArr)) {
            groupIds = groupIdArr.split("-");
        }else{
            groupIds = new String[]{};
        }

        admin = userType.equals(UserType.Admin);


    }

    public boolean isAdmin() {
        return admin;
    }
}
