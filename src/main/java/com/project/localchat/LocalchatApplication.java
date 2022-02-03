package com.project.localchat;

import com.project.localchat.entity.Users;
import com.project.localchat.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LocalchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalchatApplication.class, args);
    }

}
