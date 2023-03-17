package com.chatop.api.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
       
}
