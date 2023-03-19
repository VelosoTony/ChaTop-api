package com.chatop.api.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {

    @NotNull
    private String message;
    @NotNull
    private Integer user_id;
    @NotNull
    private Integer rental_id;
       
}
