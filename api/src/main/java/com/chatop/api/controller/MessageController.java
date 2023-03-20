package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.request.MessageRequest;
import com.chatop.api.dto.response.StringResponse;
import com.chatop.api.model.Message;
import com.chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api")
@Tag(name = "Message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/messages", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create new message", description = "Create a new message with the specified informations")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
					description = "successful operation", 
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = Message.class),
					examples = {@ExampleObject(value = "{\"message\":\"Message send with success\"}")})),
		@ApiResponse(responseCode = "401", 
					description = "unauthorized", 
					content = @Content),
        @ApiResponse(responseCode = "400", 
					description = "bad request", 
					content = @Content),
                })	                   
    public ResponseEntity<StringResponse> createRental(@Valid @RequestBody MessageRequest message) {
        
        this.messageService.save(message);

		return ResponseEntity.ok(new StringResponse("Message send with success"));
	}
    
    
}
