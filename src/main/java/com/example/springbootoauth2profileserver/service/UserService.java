package com.example.springbootoauth2profileserver.service;

import com.example.springbootoauth2profileserver.domain.User;
import com.example.springbootoauth2profileserver.domain.UserRepository;
import com.example.springbootoauth2profileserver.web.dto.LoginDto;
import com.example.springbootoauth2profileserver.web.dto.UserDto;
import com.example.springbootoauth2profileserver.web.dto.UserMapper;
import com.example.springbootoauth2profileserver.web.error.LoginFailureException;
import com.example.springbootoauth2profileserver.web.error.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerNewUserAccount(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("이미 등록된 이메일입니다.");
        }
        User user = mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        userRepository.save(user);
    }
}
