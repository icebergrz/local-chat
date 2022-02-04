package com.project.localchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class LocalchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalchatApplication.class, args);
    }

}
