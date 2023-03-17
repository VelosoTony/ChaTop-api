package com.chatop.api.dto.response;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {

    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    
}
