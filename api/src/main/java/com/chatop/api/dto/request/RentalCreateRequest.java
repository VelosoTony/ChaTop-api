package com.chatop.api.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalCreateRequest {
    
    @Schema(description = "Rental name", example = "Villa Romana")
    private String name;
    @Schema(description = "Rental surface", example = "240")
    private Double surface;
    @Schema(description = "Rental picture", example = "villa.png")
    private MultipartFile picture;
    @Schema(description = "Price", example = "1200")
    private Double price;
    @Schema(description = "Rental description", example = "Luxurious villa with swimming pool, tennis court...")
    private String description;
    
}
