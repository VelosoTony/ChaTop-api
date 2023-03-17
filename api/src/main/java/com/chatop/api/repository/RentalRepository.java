package com.chatop.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.Rental;


// This will be AUTO IMPLEMENTED by Spring into a Bean called rentalRepositery
// CRUD refers Create, Read, Update, Delete
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    
    Optional<Rental> findById(Integer id);
    
}
