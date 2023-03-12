package com.chatop.api.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.Rentals;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.service.RentalService;

@RestController
public class RentalsController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalRepository rentalRepository;

    /**
     * Read - Get all users
     * @return - An Iterable object of User full filled
     */
    @GetMapping("/rentals")
    public Iterable<Rentals> getRentals() {
        return rentalService.getRentals();
    }

     /**
     * Read - Get user by id
     * @return - An object of User full filled
     */
    @GetMapping("/rentals/{id}")
    public Optional<Rentals> getRental(@PathVariable Long id) {
        return rentalService.getRental(id);
    }
   
    @PostMapping("/rentals") 
    public @ResponseBody String addRental (@RequestParam String name, 
                                          @RequestParam Double surface,
                                          @RequestParam Double price,
                                          @RequestParam String picture,
                                          @RequestParam String description) {
      // @ResponseBody means the returned String is the response, not a view name
      // @RequestParam means it is a parameter from the GET or POST request
  
      Rentals rental = new Rentals();
      rental.createRental(name, surface, price, picture, description);
      rentalRepository.save(rental);
      return "Saved";
    }
}

