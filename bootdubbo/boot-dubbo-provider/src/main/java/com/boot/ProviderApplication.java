package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import sun.applet.Main;

@SpringBootApplication
@ImportResource("classpath:config/spring-dubbo.xml")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
