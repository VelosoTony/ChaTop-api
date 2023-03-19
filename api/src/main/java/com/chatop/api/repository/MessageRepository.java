package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    
}

