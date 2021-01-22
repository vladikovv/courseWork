package com.bfu.courseProject.controllers;

import com.bfu.courseProject.dtos.login.LoginRequestDTO;
import com.bfu.courseProject.dtos.login.LoginResponseDTO;
import com.bfu.courseProject.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.login(loginRequestDTO);
    }
}
