package com.chatop.api.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatop.api.configuration.JwtTokenUtility;
import com.chatop.api.dto.request.JwtRequest;
import com.chatop.api.dto.request.RegisterRequest;
import com.chatop.api.dto.response.JwtResponse;
import com.chatop.api.dto.response.UserResponse;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
    private UserRepository userRepository;

	@Autowired
	private JwtTokenUtility jwtTokenUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		
		if(user!=null) {
			return new org.springframework.security.core.userdetails.User(email, user.getPassword(), new ArrayList<>());
		} else {
			return null;
		}
	
	}

	public JwtResponse login(JwtRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtility.generateToken(userDetails);

		return new JwtResponse(token);
    }

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


	public JwtResponse register(RegisterRequest user) throws Exception {

		User newUser = User.builder()
			.email(user.getEmail())
			.name(user.getName())
			.password(bcryptEncoder.encode(user.getPassword()))
			.build();
		User savedUser = userRepository.save(newUser);

		String token = jwtTokenUtility.generateToken(loadUserByUsername(savedUser.getEmail()));

		return new JwtResponse(token);
		
	}

	public UserResponse me() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

		UserResponse userMeDTO = new UserResponse();
			userMeDTO.setId(user.getId());
			userMeDTO.setName(user.getName());	
			userMeDTO.setEmail(user.getEmail());
			userMeDTO.setCreated_at(user.getCreated_at());
			userMeDTO.setUpdated_at(user.getUpdated_at());
		
		return userMeDTO;
	}
}