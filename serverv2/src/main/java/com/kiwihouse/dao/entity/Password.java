package com.kiwihouse.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Password {
	private String oldPassword;
	private String newPassword;
	private Integer uid;
}
