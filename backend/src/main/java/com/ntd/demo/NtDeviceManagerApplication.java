package com.ntd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.ntd" })
@SpringBootApplication
public class NtDeviceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NtDeviceManagerApplication.class, args);
    }

}
