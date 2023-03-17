package com.chatop.api.model;


import java.time.LocalDateTime ;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
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
@Table(name = "rentals") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rental {

    @Id // clé primaire de la table 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Integer id;
    
    private String name;

    private Double surface;

    private Double price;

    private String picture;

    private String description;

    private Integer owner_id;

    @CreatedDate
    private LocalDateTime  created_at;

    @LastModifiedDate
    private LocalDateTime  updated_at;
    
}