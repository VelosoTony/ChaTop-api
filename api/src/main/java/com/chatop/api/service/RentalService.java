package com.chatop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatop.api.model.Rentals;
import com.chatop.api.repository.RentalRepository;


import lombok.Data;

@Data
@Service // Spécialisation de @Component
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Optional<Rentals> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    public Iterable<Rentals> getRentals() {
        return rentalRepository.findAll();
    }

    
    public Rentals saveRental(Rentals rental) {
        Rentals savedRental = rentalRepository.save(rental);
        return savedRental;
    }  
}
