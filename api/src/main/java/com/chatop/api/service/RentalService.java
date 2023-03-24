package com.chatop.api.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.request.RentalUpdateRequest;
import com.chatop.api.dto.response.RentalListResponse;
import com.chatop.api.dto.response.RentalResponse;
import com.chatop.api.dto.response.StringResponse;
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

    public Rental getRental(int id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        return rental.orElse(null);
    }

    public RentalListResponse getRentals() {

        List<Rental> rental = rentalRepository.findAll();

        return new RentalListResponse(rental);
    }

    @Transactional
    public StringResponse save(RentalResponse rental) {
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
        this.rentalRepository.save(newRental);
        return new StringResponse("Rental created !");
    }

    @Transactional
    public StringResponse updateRental(int id, RentalUpdateRequest rental) {

        Optional<Rental> storedRental = rentalRepository.findById(id);
        if (storedRental.isPresent()) {
            Rental currentRental = storedRental.get();
            currentRental.setName(rental.getName());
            currentRental.setSurface(rental.getSurface());
            currentRental.setPrice(rental.getPrice());
            currentRental.setDescription(rental.getDescription());

            this.rentalRepository.save(currentRental);
            return new StringResponse("Rental updated !");
        } else {
            return new StringResponse("Rental does not exist !");
        }

    }

}
