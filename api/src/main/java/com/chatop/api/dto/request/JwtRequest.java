package com.chatop.api.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
	
	@Email(regexp=".*@.*\\..*", message = "Email should be valid")
	private String email;
	private String password;
	
}