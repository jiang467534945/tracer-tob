package com.ow.tracer.mantuoluo.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
@EnableOAuth2Sso
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {
        "com.ow.tracer.common.config", "com.ow.tracer.mantuoluo.member.dsm", "com.ow.tracer.mantuoluo.webapp.config","com.ow.tracer.mantuoluo.member.admin.rest"})
@MapperScan("com.ow.tracer.mantuoluo.member.dsm.mapper*")

public class TracerMantuoluoAdminRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracerMantuoluoAdminRestApplication.class, args);
    }

}
