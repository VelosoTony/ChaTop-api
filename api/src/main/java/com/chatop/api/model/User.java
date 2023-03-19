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
@Table(name = "users") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Schema(description = "User identifier", example = "1")
    @Id // clé primaire de la table 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Integer id;
    
    @Schema(description = "User name", example = "Robert")
    private String name;

    @Schema(description = "User email", example = "Robert@mail.com")
    private String email;

    @Schema(description = "User password", example = "1243pass")
    private String password;

    @Schema(description = "date this user was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    private LocalDateTime  created_at;

    @Schema(description = "date this rental was updated", example = "2023-03-18T00:23:42")
    @LastModifiedDate
    private LocalDateTime  updated_at;

}
