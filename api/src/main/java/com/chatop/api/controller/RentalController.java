package com.chatop.api.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.api.dto.request.RentalCreateRequest;
import com.chatop.api.dto.request.RentalUpdateRequest;
import com.chatop.api.dto.response.RentalListResponse;
import com.chatop.api.dto.response.RentalResponse;

import com.chatop.api.dto.response.StringResponse;
import com.chatop.api.model.Rental;
import com.chatop.api.service.FileService;
import com.chatop.api.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.ExampleObject;
@RestController
@RequestMapping("/api")
@Tag(name = "Rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private FileService fileService;

    @GetMapping("/rentals")
    @Operation(summary = "Get list orentals", description = "Retrieve information of all rentals")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "successful operation", 
                                        content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = RentalListResponse.class))),
		@ApiResponse(responseCode = "401", description = "unauthorized", 
					                    content = @Content)})	
    public RentalListResponse getRentals() {

        return rentalService.getRentals(); 

    }


    @GetMapping("/rentals/{id}")
    @Operation(summary = "Get rental by id", description = "Retrieve rental information specified by his id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = Rental.class))),
		@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content)})	
    public ResponseEntity<Rental> getRental(@PathVariable Integer id) {
       
        return ResponseEntity.ok(rentalService.getRental(id));

    }
   
    @PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create new rental", description = "Create a new rental with the specified informations")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = StringResponse.class),
					examples = {@ExampleObject(value = "{\"message\":\"Rental created !\"}")})),
		@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content)})	
                    
        public ResponseEntity<StringResponse> addRental(
                            @RequestBody RentalCreateRequest rentalCreateRequest) throws IOException {
        
            RentalResponse rental = new RentalResponse();
                rental.setName(rentalCreateRequest.getName());
                rental.setSurface(rentalCreateRequest.getSurface());
                rental.setPrice(rentalCreateRequest.getPrice());
                rental.setDescription(rentalCreateRequest.getDescription());     
                
                // uploadPicture and return FileUrl if success
                String picture = this.fileService.uploadPicture(rentalCreateRequest.getPicture());

                rental.setPicture(picture);
            // picture uploaded with success               
            if (rental.getPicture() != null) {

                return ResponseEntity.ok(this.rentalService.save(rental));

            } else {

                return ResponseEntity.ok(new StringResponse("Error uploading file picture."));
            }                 

           
 
    } 
    
    @PutMapping("/rentals/{id}")
    @Operation(summary = "Update rental by id", description = "Update a rental with the rental id and specified informations")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = StringResponse.class),
					examples = {@ExampleObject(value = "{\"message\":\"Rental updated !\"}")})),
		@ApiResponse(responseCode = "401", description = "unauthorized", content = @Content)})	
    public  ResponseEntity<StringResponse> updateRental (@PathVariable(value = "id") Integer id,
                                        @RequestBody RentalUpdateRequest rentalUpdateRequest) {
                                    
        return ResponseEntity.ok(this.rentalService.updateRental(id, rentalUpdateRequest));
    }
}

