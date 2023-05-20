package com.gu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gu.utils.Neo4jUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class GraphUpdaterApplicationTests {

    @Autowired
    private Neo4jUtils neo4jUtils;

    @Test
    void contextLoads() throws Exception{
        neo4jUtils.addSourceNodes();
        neo4jUtils.addTranslationNodes();
//        neo4jUtils.addWordNodes();
    }

    @Test
    void Test(){
    }

}
