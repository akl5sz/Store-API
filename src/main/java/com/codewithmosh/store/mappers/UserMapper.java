package com.codewithmosh.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;

@Mapper(componentModel = "spring")  //do this so spring can create beans of this type at runtime
//when we compile our app, mapstruct will automatically implement this interface
public interface UserMapper {
    @Mapping(target="createdAt", expression="java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
}
