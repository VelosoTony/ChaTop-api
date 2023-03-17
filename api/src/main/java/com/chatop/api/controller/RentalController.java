package com.chatop.api.controller;

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

import com.chatop.api.dto.response.RentalResponse;
import com.chatop.api.dto.response.StringResponse;
import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    /**
     * Read - Get all users
     * @return - An Iterable object of User full filled
     */
    @GetMapping("/rentals")
    public Iterable<Rental> getRentals() {
        return rentalService.getRentals();
    }

     /**
     * Read - Get user by id
     * @return - An object of User full filled
     */
    @GetMapping("/rentals/{id}")
    public Rental getRental(@PathVariable Integer id) {
        Optional<Rental> rental = rentalService.getRental(id);
		if(rental.isPresent()) {
			return rental.get();
		} else {
			return null;
        }
    }
   
    @PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

            this.rentalService.save(rental);

            return ResponseEntity.ok(new StringResponse("Rental created !"));
    }
    
    @PutMapping("/rentals/{id}")
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

