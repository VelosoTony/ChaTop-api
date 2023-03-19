package com.chatop.api.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    private String name;
    @NotNull
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    private String email;
    @NotNull
    private String password;
       
}
