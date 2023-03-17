package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.request.JwtRequest;
import com.chatop.api.dto.request.RegisterRequest;
import com.chatop.api.dto.response.JwtResponse;
import com.chatop.api.dto.response.UserResponse;
import com.chatop.api.service.JwtUserDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> generateAuthenticationToken (
		@RequestBody JwtRequest authenticationRequest)	throws Exception {

		return ResponseEntity.ok(userDetailsService.login(authenticationRequest));
	}

	@PostMapping("/register")
	public ResponseEntity<JwtResponse> register (
		@Valid @RequestBody RegisterRequest userDto) throws Exception {

		return ResponseEntity.ok(userDetailsService.register(userDto));
	}

	@GetMapping("/me")
    public ResponseEntity<UserResponse> me()  throws Exception {

		return ResponseEntity.ok(userDetailsService.me());
	}

}
