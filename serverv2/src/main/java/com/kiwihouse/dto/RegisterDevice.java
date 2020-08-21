package com.kiwihouse.dto;

import lombok.*;

import java.util.HashMap;

/**
 * {
 *     "title": "sn号",
 *     "protocol": "LWM2M",
 *     "auth_info": {"865933030000000": "86593303"},
 *     "obsv": true
 * }
 *
 * {
 *      "title":"sn",
 *      "protocol":"LWM2M",
 *      "auth_info":{"865933030000000":"86593303"},
 *      "obsv":"true"
 * }
 *
 * @author yjzn
 * @date 2020-1-8 15:48:01
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegisterDevice {

    private String title;
    private String protocol;
    private HashMap<String,String> auth_info;
    private String obsv;

}
