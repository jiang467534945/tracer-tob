package com.ow.tracer.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan(basePackages = {
        "com.ow.tracer.common.config", "com.ow.tracer.common.resolver","com.ow.tracer.file.api"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})

public class TracerFileRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracerFileRestApplication.class, args);
    }
}
