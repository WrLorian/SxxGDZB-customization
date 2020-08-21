package com.kiwihouse.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *   账户
 * @author tomsun28
 * @date 16:25 2018/2/27
 */
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;
    private String password;
    private String salt;

    public Account(String appId, String password, String salt) {
        this.appId = appId;
        this.password = password;
        this.salt = salt;
    }

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
    


}
