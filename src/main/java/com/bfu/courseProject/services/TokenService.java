package com.bfu.courseProject.services;

import com.bfu.courseProject.entities.User;
import com.bfu.courseProject.security.CustomUserPrincipal;

public interface TokenService {
    String generateToken(User user);
    CustomUserPrincipal parseToken(String token);
}
