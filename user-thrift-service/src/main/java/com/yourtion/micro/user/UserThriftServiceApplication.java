package com.yourtion.micro.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class UserThriftServiceApplication {

    public static void main(String args[]) {
//        SpringApplication.run(ServiceApplication.class, args);
        new SpringApplicationBuilder()
                .sources(UserThriftServiceApplication.class)
                .web(false)
                .run(args);
    }

}
