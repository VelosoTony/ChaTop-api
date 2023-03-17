package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepositery
// CRUD refers Create, Read, Update, Delete
public interface  UserRepository extends JpaRepository<User, Integer> { 
    
    User findByEmail (String email);

    
}
