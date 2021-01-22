package com.bfu.courseProject.services;

import com.bfu.courseProject.dtos.login.LoginRequestDTO;
import com.bfu.courseProject.dtos.login.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
