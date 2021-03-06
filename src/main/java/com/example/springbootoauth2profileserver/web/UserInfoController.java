package com.example.springbootoauth2profileserver.web;

import com.example.springbootoauth2profileserver.domain.User;
import com.example.springbootoauth2profileserver.service.UserService;
import com.example.springbootoauth2profileserver.web.dto.UserDto;
import com.example.springbootoauth2profileserver.web.dto.UserProfile;
import com.example.springbootoauth2profileserver.web.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserInfoController {

    private final UserService userService;

    @GetMapping("/userinfo")
    public ResponseEntity<?> userInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();
        UserProfile profile = new UserProfile(name);

        OAuth2Authentication oAuth2Authentication;
        if (authentication instanceof OAuth2Authentication) {
            oAuth2Authentication = (OAuth2Authentication) authentication;
            Set<String> scopes = oAuth2Authentication.getOAuth2Request().getScope();

            Optional<User> user = userService.findByEmail(name);
            if (user.isEmpty()) {
                return ResponseEntity.ok(profile);
            }

            if (scopes.contains("email")) {
                profile.setEmail(user.get().getEmail());
            }
        }

        return ResponseEntity.ok(profile);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto) {

        GenericResponse response = new GenericResponse("", "");

        userService.registerNewUserAccount(userDto);

        return ResponseEntity.ok(response);
    }
}
