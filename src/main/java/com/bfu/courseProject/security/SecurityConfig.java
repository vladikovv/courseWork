package com.bfu.courseProject.security;

import com.bfu.courseProject.security.filters.JWTAuthenticationFilter;
import com.bfu.courseProject.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    @Autowired
    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers("/login",  "/v2/api-docs", "/configuration/**",
                        "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTAuthenticationFilter(tokenService),
                        UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
