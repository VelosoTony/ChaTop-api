package com.chatop.api.dto.response;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime  updated_at;
       
}
