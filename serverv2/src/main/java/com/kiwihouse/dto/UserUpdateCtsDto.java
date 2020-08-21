package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 更新用户对应的联系人
 * @author yjzn
 * @date 2019-12-20-下午 8:26
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
public class UserUpdateCtsDto {

    private String ctsName;
    private String ctsId;
    private String ctsPhone;
    private String userId;
}
