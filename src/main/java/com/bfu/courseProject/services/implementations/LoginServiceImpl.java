package com.bfu.courseProject.services.implementations;

import com.bfu.courseProject.dtos.login.LoginRequestDTO;
import com.bfu.courseProject.dtos.login.LoginResponseDTO;
import com.bfu.courseProject.entities.User;
import com.bfu.courseProject.exceptions.InvalidCredentialsException;
import com.bfu.courseProject.repositories.UserRepository;
import com.bfu.courseProject.services.LoginService;
import com.bfu.courseProject.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, TokenService tokenService,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    private void postConstruct() {
        if(userRepository.findAll().size() == 0) {
            String hashedAdminPassword = bCryptPasswordEncoder.encode("admin");
            User admin = new User((long)1, "no city", "admin", "admin",
                    "no number", "admin@email.com", Instant.now(),
                    hashedAdminPassword, true);
            userRepository.save(admin);
        }
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = this.userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid login credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid login credentials");
        }

        return createLoginResponse(user);
    }

    private LoginResponseDTO createLoginResponse(User user) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(
                tokenService.generateToken(user)
        );
        return loginResponseDTO;
    }
}
