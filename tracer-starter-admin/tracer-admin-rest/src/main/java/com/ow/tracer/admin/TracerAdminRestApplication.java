package com.ow.tracer.admin;

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
        "com.ow.tracer.common.config", "com.ow.tracer.common.resolver","com.ow.tracer.admin"})
@MapperScan("com.ow.tracer.admin.account.mapper*")
public class TracerAdminRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracerAdminRestApplication.class, args);
    }
}
