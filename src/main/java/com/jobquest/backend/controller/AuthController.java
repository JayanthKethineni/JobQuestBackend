package com.jobquest.backend.controller;

import com.jobquest.backend.payload.request.SignupRequest;
import com.jobquest.backend.payload.request.LoginRequest;
import com.jobquest.backend.payload.response.MessageResponse;
import com.jobquest.backend.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {
        System.out.println("Signup endpoint hit!");

        // Normally you’d save the user to DB here — this is just a working dummy
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        System.out.println("Login endpoint hit!");

        // Normally you'd validate the credentials and return JWT
        return ResponseEntity.ok(new JwtResponse("fake-jwt-token-goes-here"));
    }
}
