package com.url.shortmyurl.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role;
}
