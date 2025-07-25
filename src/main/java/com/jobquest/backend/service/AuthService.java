package com.jobquest.backend.service;

import com.jobquest.backend.dto.RegisterRequest;
import com.jobquest.backend.model.User;
import com.jobquest.backend.repository.UserRepository;
import com.jobquest.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    public User register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }

    // LOGIN AUTHENTICATE AND RETURN TOKEN
    public String authenticate(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Invalid email or password");
    }
}
