package com.chatop.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.response.UserResponse;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;


@Service // Spécialisation de @Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserResponse getUserById(Integer id) {

        Optional<User> user = userRepository.findById(id);
    
            User usr = user.get();
            UserResponse userResp = new UserResponse();
                userResp.setId(usr.getId());
                userResp.setName(usr.getName());	
                userResp.setEmail(usr.getEmail());
                userResp.setCreated_at(usr.getCreated_at());
                userResp.setUpdated_at(usr.getUpdated_at());
            
            return userResp;

    }


    public Iterable<User> getUsers() {
        return userRepository.findAll();
    } 
    
}

