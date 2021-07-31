package com.example.springbootoauth2profileserver.web.dto;

import com.example.springbootoauth2profileserver.web.annotation.PasswordMatches;
import com.example.springbootoauth2profileserver.web.annotation.ValidEmail;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@PasswordMatches
@Getter
@Setter
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    private String roles;
}
