package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.response.UserResponse;
import com.chatop.api.service.UserService;

@RestController
@RequestMapping("/api")

public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Read - Get user by id
     * @return - An object of User full filled
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) throws Exception {

        return  ResponseEntity.ok(userService.getUserById(id));

    }

}
