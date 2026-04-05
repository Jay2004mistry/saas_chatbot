package com.chatbot.chatbot_backend.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClientResponse {
    private String id;
    private String businessName;
    private String email;
    private String phoneNumber;
    private String planType;
    private boolean active;
    private String apiKey;
    private LocalDateTime createdAt;
    private LocalDateTime activatedAt;
}