package com.codewithmosh.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;

@Mapper(componentModel = "spring")  //do this so spring can create beans of this type at runtime
//when we compile our app, mapstruct will automatically implement this interface
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
