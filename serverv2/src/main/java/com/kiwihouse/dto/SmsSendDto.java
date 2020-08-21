package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-02-21-下午 3:41
 */
@Accessors(chain = true)
@Getter
@Setter
public class SmsSendDto {

    private String AccountId;
    private String AccessKey;
    private long Timestamp;
    private long Random;
    private String ProductId;
    private String PhoneNos;
    private String Content;

    @Override
    public String toString() {
        return "{" +
                "\"AccountId\":\"" + AccountId + '\"' +
                ", \"AccessKey\":\"" + AccessKey + '\"' +
                ", \"Timestamp\":" + Timestamp +
                ", \"Random\":" + Random +
                ", \"ProductId\":\"" + ProductId + '\"' +
                ", \"PhoneNos\":\"" + PhoneNos + '\"' +
                ", \"Content\":\"" + Content + '\"' +
                '}';
    }
}
