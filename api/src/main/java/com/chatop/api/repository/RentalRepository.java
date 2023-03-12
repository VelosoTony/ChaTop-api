package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;


import com.chatop.api.model.Rentals;


// This will be AUTO IMPLEMENTED by Spring into a Bean called rentalRepositery
// CRUD refers Create, Read, Update, Delete

public interface RentalRepository extends CrudRepository<Rentals, Long>{
    
}
