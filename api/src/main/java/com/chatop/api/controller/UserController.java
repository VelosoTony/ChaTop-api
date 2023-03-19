package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.response.UserResponse;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api")
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Read - Get user by id
     * @return - An object of User full filled
     */
    @GetMapping("/user/{id}")
    @Operation(summary = "Get user by id", description = "Retrieve account information about user specified by his id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = UserResponse.class))),
		@ApiResponse(responseCode = "401", 
					description = "unauthorized", 
					content = @Content)})	
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) throws Exception {

        return  ResponseEntity.ok(userService.getUserById(id));

    }

}
