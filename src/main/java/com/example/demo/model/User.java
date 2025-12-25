package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users",
       uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;   // email or username

    @Column(nullable = false)
    private String password;   // BCrypt encoded

    private String role = "USER";
    private Boolean active = true;

    // Constructors
    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = (role != null) ? role : "USER";
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}

