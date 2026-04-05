package com.chatbot.chatbot_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatbot.chatbot_backend.entity.Client;

public interface  ClientRepository extends  JpaRepository<Client, String> {
    Optional<Client> getClientByEmail(String email);
    Optional<Client> findByApiKey(String apiKey);
    boolean existsByEmail(String email);

}
