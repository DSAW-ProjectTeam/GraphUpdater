package com.gu.config;

import com.gu.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.Neo4jClient;

@Configuration
@Slf4j
public class Neo4jConfig {

    @Autowired
    private Constant constant;


    // Neo4j数据源
    @Bean
    public Neo4jClient neo4jClient(){
        Neo4jClient neo4jClient = null;

        try {
            Driver driver = GraphDatabase.driver(constant.getUrl(), AuthTokens.basic(constant.getUsername(), constant.getPassword()));
            neo4jClient = Neo4jClient.create(driver);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return neo4jClient;
    }
}
