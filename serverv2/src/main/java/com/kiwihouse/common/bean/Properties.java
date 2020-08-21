package com.kiwihouse.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yjzn
 * @date 2020-1-10 13:32:03
 */
@Configuration
@ToString
@Getter
@Setter
public class Properties {

    @Value("${server.port}")
    private String port;
}
