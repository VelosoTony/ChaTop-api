package com.chatop.api.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.response.RentalListResponse;
import com.chatop.api.dto.response.RentalResponse;
import com.chatop.api.dto.response.RentalsResponse;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Service // Spécialisation de @Component
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Rental> getRental(final Integer id) {
        return rentalRepository.findById(id);
    }

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    @Transactional
    public Rental save(RentalResponse rental) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        Integer userId = user.getId();
		Rental newRental = Rental.builder()
            .name(rental.getName())
            .surface(rental.getSurface())
            .price(rental.getPrice())
            .picture(rental.getPicture())
            .description(rental.getDescription())
            .owner_id(userId)
            .build();
		return rentalRepository.save(newRental);
	}

    @Transactional
    public String updateRental(int id, RentalResponse rental) {
        
        Optional<Rental> storedRental = rentalRepository.findById(id);
        if(storedRental.isPresent()) {
            Rental currentRental = storedRental.get();
            currentRental.setName(rental.getName());
            currentRental.setSurface(rental.getSurface());
            currentRental.setPrice(rental.getPrice());
            currentRental.setDescription(rental.getDescription());
            
            this.rentalRepository.save(currentRental);
            return "Rental updated !";
        } else {
            return "Rental does not exist !";
        }

	

    }



}
