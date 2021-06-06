package com.jiketime.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liaock on 2021/6/3
 **/

@ConfigurationProperties(prefix = "school")
@Data
@Setter
@Getter
public class SchoolProperties {
    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String telNo;

}
