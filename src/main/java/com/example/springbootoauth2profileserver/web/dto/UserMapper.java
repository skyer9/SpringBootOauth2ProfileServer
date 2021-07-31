package com.example.springbootoauth2profileserver.web.dto;

import com.example.springbootoauth2profileserver.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}
