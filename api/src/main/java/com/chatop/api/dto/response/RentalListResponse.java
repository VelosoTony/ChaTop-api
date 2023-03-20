package com.chatop.api.dto.response;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.chatop.api.model.Rental;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalListResponse {
    @Schema(description = "Rental list")
    private List<Rental> rentals;
    
}