package com.chatop.api.dto.response;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {
    @Schema(description = "Rental name", example = "Villa Romana")
    private String name;
    @Schema(description = "Rental surface", example = "240")
    private Double surface;
    @Schema(description = "Price", example = "599000")
    private Double price;
    @Schema(description = "Rental picture", example = "villa.png")
    private String picture;
    @Schema(description = "Rental description", example = "Luxurious villa with swimming pool, tennis court...")
    private String description;
    
}
