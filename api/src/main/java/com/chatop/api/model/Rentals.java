package com.chatop.api.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data // annotation Lombok qui évite d'ajouter les getters et setters.
@Entity // annotation qui indique que la classe correspond à une table de la BD.
@Table(name = "rentals") // indique le nom de la table associée
public class Rentals {

    @Id // clé primaire de la table 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Long id;
    
    private String name;

    private Double surface;

    private Double price;

    private String picture;

    private String description;

    @Column(name="owner_id")
    private Integer ownerId;

    @Column(name="created_at")
    private Date createdDate;

    @Column(name="updated_at")
    private Date updatedDate;
    
    public void createRental(String name, Double surface, Double price, String picture, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.ownerId = 1;
    }

}