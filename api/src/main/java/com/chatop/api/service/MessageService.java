package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.request.MessageRequest;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Service // Spécialisation de @Component
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    @Transactional
    public Message save(MessageRequest message) {

        Message newMessage = Message.builder()
            .message(message.getMessage())
            .user_id(message.getUser_id())
            .rental_id(message.getRental_id())
            .build();

        return messageRepository.save(newMessage);
    }
    
}
