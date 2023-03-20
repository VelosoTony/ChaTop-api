package com.chatop.api.model;

import java.time.LocalDateTime ;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data // annotation Lombok qui évite d'ajouter les getters et setters.
@Builder
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "messages") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class Message {

    @Schema(description = "Message identifier", example = "1")
    @Id // clé primaire de la table 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Integer id;
    
    @Schema(description = "Rental identifier", example = "1")
    private Integer rental_id;

    @Schema(description = "User identifier", example = "1")
    private Integer user_id;

    @Schema(description = "Message content", example = "this is the message.")
    private String message;

    @Schema(description = "date this message was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    private LocalDateTime  created_at;

    @Schema(description = "date this message was updated", example = "2023-03-18T00:23:42")
    @LastModifiedDate
    private LocalDateTime  updated_at;
    
}
