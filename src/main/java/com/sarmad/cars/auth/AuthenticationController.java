package com.sarmad.cars.auth;

import com.sarmad.cars.auth.jwt.JwtAuthenticationService;
import com.sarmad.cars.monitor.LogAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
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
        try {
            return ResponseEntity.ok(this.authService.register(req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(AuthenticationResponse.builder()
                    .message("User already exists")
                    .build());
        }
    }

    @PostMapping("/login")
    @LogAction("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest req
    ) {
        try {
            return ResponseEntity.ok(this.authService.login(req));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(AuthenticationResponse.builder()
                    .message("Invalid login credentials")
                    .build());
        }

    }
}
