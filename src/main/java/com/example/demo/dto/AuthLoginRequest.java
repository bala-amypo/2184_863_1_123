package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthLoginRequest {

    @NotBlank(message = "Username or email must not be blank")
    private String usernameOrEmail;

    @NotBlank(message = "Password must not be blank")
    private String password;

    public AuthLoginRequest() {
    }

    public AuthLoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
