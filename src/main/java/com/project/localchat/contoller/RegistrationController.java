package com.project.localchat.contoller;

import com.project.localchat.entity.Roles;
import com.project.localchat.entity.Users;
import com.project.localchat.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Users user, Map<String, Object> model, HttpServletRequest request) {
        Users userFromDb = userRepository.findByUsername(user.getUsername());
        Users emailFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb != null || emailFromDb != null) {
            model.put("message", "Пользователь с таким именем/почтой существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Roles.USER));
        userRepository.save(user);

        try {
            request.login(user.getUsername(), user.getDefaultPassword());
        } catch(ServletException ex) {
            System.out.println("Не получается войти после регистрации\n" + user.getUsername() + ", " + user.getDefaultPassword() + ", " + user.getPassword());
        }

        user.setDefaultPassword("null");

        return "redirect:/chat";
    }
}
