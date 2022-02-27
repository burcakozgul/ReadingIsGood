package com.example.readingisgood.controller;

import com.example.readingisgood.exception.AuthException;
import com.example.readingisgood.security.JwtUtils;
import com.example.readingisgood.security.UserDetailsServiceImpl;
import com.example.readingisgood.types.requests.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signIn")
    public String signIn(@RequestBody SignInRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthException("Authentication error", "ERR_A1");
        }
        return jwtUtils.generateToken(userDetailsService.loadUserByUsername(request.getMail()));
    }
}
