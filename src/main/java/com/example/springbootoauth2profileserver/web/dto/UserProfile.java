package com.example.springbootoauth2profileserver.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfile {

    private String name;
    private String email;

    public UserProfile(String name) {
        this.name = name;
    }
}
