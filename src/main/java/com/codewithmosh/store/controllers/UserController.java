package com.codewithmosh.store.controllers;


import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;

import lombok.*;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @GetMapping
    public Iterable<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "", name = "sort") String sort){ 
        if(!Set.of("name", "email").contains(sort)){
            sort = "name";
        }
        return userRepository.findAll(Sort.by(sort))
        .stream()
        .map(userMapper::toDto) // or user -> userMapper.toDto(user)
        .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody RegisterUserRequest request,
        UriComponentsBuilder uriBuilder){
        User user = userMapper.toEntity(request);
        userRepository.save(user);

        UserDto userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id,
                            @RequestBody UpdateUserRequest request){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        userMapper.update(request, user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
