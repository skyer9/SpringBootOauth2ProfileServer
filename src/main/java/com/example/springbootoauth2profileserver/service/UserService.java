package com.example.springbootoauth2profileserver.service;

import com.example.springbootoauth2profileserver.domain.User;
import com.example.springbootoauth2profileserver.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByUid(String email) {
        return userRepository.findByUid(email);
    }
}
