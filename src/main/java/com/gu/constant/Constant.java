package com.gu.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "constant.neo4j")
@Data
public class Constant {

    private String username;

    private String password;

    private String url;
}
