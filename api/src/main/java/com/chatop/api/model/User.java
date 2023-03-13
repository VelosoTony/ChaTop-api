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
@Table(name = "users") // indique le nom de la table associée
public class User {

    @Id // clé primaire de la table 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id est auto-incrémenté
    private Long id;
    
    private String email;

    private String name;

    private String password;

    @Column(name="created_at")
    private Date createdDate;

    @Column(name="updated_at")
    private Date updatedDate;
    
    
    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
