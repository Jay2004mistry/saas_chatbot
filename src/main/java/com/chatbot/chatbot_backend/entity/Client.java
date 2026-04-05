package com.chatbot.chatbot_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Entity
@Data
@Table(name = "clients")
@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String businessName;

    @Email
    @Column(unique = true)   
    private String email;

    private String password;
    private String phoneNumber;

    // stater/ growth /business
    private String plan="starter";
    private boolean isActive=true;
    private LocalDateTime createdAt=LocalDateTime.now();  
    private LocalDateTime activatedAt;

    // api key unique key for their chat widget
    private String apiKey; 
}
