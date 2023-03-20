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
@Table(name = "rentals") // indique le nom de la table associée
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rental {

    @Schema(description = "Rental identifier", example = "1")
    @Id // primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id is auto-increment
    private Integer id;
    
    @Schema(description = "Rental name", example = "Villa Romana")
    private String name;

    @Schema(description = "Rental surface", example = "240")
    private Double surface;

    @Schema(description = "Price", example = "1900")
    private Double price;

    @Schema(description = "Rental picture", example = "villa.png")
    private String picture;

    @Schema(description = "Rental description", example = "Luxurious villa with swimming pool, tennis court...")
    private String description;

    @Schema(description = "Owner identifier", example = "1")
    private Integer owner_id;

    @Schema(description = "date this rental was created", example = "2023-03-18T00:23:42")
    @CreatedDate
    private LocalDateTime  created_at;

    @Schema(description = "date this rental was updated", example = "2023-03-18T00:23:42")
    @LastModifiedDate
    private LocalDateTime  updated_at;
    
}