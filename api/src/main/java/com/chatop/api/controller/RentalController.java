package com.chatop.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.dto.response.RentalListResponse;
import com.chatop.api.dto.response.RentalResponse;
import com.chatop.api.dto.response.RentalsResponse;
import com.chatop.api.dto.response.StringResponse;
import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

// TODO - swagger

@RestController
@RequestMapping("/api")
@Tag(name = "Rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    /**
     * Read - Get all rentals
     * @return - An Iterable object of Rental full filled
     */
    @GetMapping("/rentals")
    public Map<String, List<Rental> > getRentals() {
        return Map.of("rentals", rentalService.getRentals());
    }

     /**
     * Read - Get rental by id
     * @return - An object of rental full filled
     */
    @GetMapping("/rentals/{id}")
    @Operation(summary = "Get rental by id", description = "Retrieve rentam information specified by his id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = Rental.class))),
		@ApiResponse(responseCode = "401", 
					description = "unauthorized", 
					content = @Content)})	
    public Rental getRental(@PathVariable Integer id) {
        Optional<Rental> rental = rentalService.getRental(id);
		if(rental.isPresent()) {
			return rental.get();
		} else {
			return null;
        }
    }
   
    @PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create new rental", description = "Create a new rental with the specified informations")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = StringResponse.class),
					examples = {@ExampleObject(value = "{\"message\":\"Rental created !\"}")})),
		@ApiResponse(responseCode = "401", 
					description = "unauthorized", 
					content = @Content)})	
                    
    public ResponseEntity<StringResponse> addRental(
                            @RequestParam("name") String name,
                            @RequestParam("surface") Double surface,
                            @RequestParam("price") Double price,
                            @RequestParam("picture") MultipartFile picture,
                            @RequestParam("description") String description ) {

        RentalResponse rental = new RentalResponse();
            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setPicture(picture.getOriginalFilename());
            rental.setDescription(description);                                   
// TODO - routine to upload picture file
            this.rentalService.save(rental);
            // Map<String, String> map = new HashMap<>();
            // map.put("message","Rental created !");                    
            // return ResponseEntity.ok(Map.of("message","Rental created !"));
            return ResponseEntity.ok(new StringResponse("Rental created !"));
    }
    
    @PutMapping("/rentals/{id}")
    @Operation(summary = "Update rental by id", description = "Update a rental with the rental id and specified informations")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = StringResponse.class),
					examples = {@ExampleObject(value = "{\"message\":\"Rental updated !\"}")})),
		@ApiResponse(responseCode = "401", 
					description = "unauthorized", 
					content = @Content)})	
    public  ResponseEntity<?> updateRental (@PathVariable(value = "id") Integer id,
                                        @RequestParam String name, 
                                        @RequestParam Double surface,
                                        @RequestParam Double price,
                                        @RequestParam String description) {
      // @ResponseBody means the returned String is the response, not a view name
      // @RequestParam means it is a parameter from the PUT request

      RentalResponse updateRental = RentalResponse.builder()
        .name(name)
        .surface(surface)
        .price(price)
        .description(description)
        .build();
                                        
        this.rentalService.updateRental(id, updateRental);

        return ResponseEntity.ok(new StringResponse("Rental updated !"));
    }
}

