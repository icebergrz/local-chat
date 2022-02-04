package com.project.localchat.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class Users{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String password;
    private boolean active;
    private String firstName;
    private String lastName;
    private String email;

    @Transient
    private String confirmPassword;
    @Transient
    private String defaultPassword;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.defaultPassword = password;
        this.password = encoder.encode(password);
    }
}

