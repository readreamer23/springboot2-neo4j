package com.modules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Neo4jApplication {

    public static void main(String[] args) {
        log.info("......启动开始");
        SpringApplication.run(Neo4jApplication.class, args);
        log.info("......启动成功");
    }
}
