package com.gu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
//@EnableJpaRepositories
public class GraphUpdaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphUpdaterApplication.class, args);
    }

}
