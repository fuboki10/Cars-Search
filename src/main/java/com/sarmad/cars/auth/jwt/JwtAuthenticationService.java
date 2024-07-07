package com.sarmad.cars.auth.jwt;

import com.sarmad.cars.auth.AuthenticationResponse;
import com.sarmad.cars.auth.LoginRequest;
import com.sarmad.cars.auth.RegisterRequest;
import com.sarmad.cars.models.User;
import com.sarmad.cars.models.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse login(LoginRequest request) {
        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        var user = userRepository.findByLoginId(request.getLoginId()).orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = this.jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .message("Login successful")
                .tokenType("Bearer")
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println("RegisterRequest: " + request);
        var user = User.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        userRepository.save(user);

        var jwtToken = this.jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .message("Registration successful")
                .tokenType("Bearer")
                .build();
    }
}
