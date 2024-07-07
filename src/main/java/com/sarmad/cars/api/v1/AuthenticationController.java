package com.sarmad.cars.api.v1;

import com.sarmad.cars.auth.AuthenticationResponse;
import com.sarmad.cars.auth.LoginRequest;
import com.sarmad.cars.auth.RegisterRequest;
import com.sarmad.cars.auth.jwt.JwtAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtAuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest req
    ) {
        return ResponseEntity.ok(this.authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest req
    ) {
        return ResponseEntity.ok(this.authService.login(req));
    }
}
