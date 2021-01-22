package com.bfu.courseProject.security.filters;

import com.bfu.courseProject.security.CustomUserPrincipal;
import com.bfu.courseProject.services.TokenService;
import javassist.Loader;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public JWTAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken token = createToken(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        CustomUserPrincipal userPrincipal = tokenService.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userPrincipal.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        } else {
            authorities.add(new SimpleGrantedAuthority("user"));
        }

        return new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
    }
}
