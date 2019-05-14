package com.ow.tracer.mantuoluo.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {
        "com.ow.tracer.common.config", "com.ow.tracer.mantuoluo.member.dsm", "com.ow.tracer.mantuoluo.webapp.config","com.ow.tracer.mantuoluo.member.web"})
@MapperScan("com.ow.tracer.mantuoluo.member.dsm.mapper*")

public class TracerMantuoluoWabappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracerMantuoluoWabappApplication.class, args);
    }

}
