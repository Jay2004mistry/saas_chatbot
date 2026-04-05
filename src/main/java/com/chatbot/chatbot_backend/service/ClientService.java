package com.chatbot.chatbot_backend.service;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatbot.chatbot_backend.dto.ClientResponse;
import com.chatbot.chatbot_backend.dto.RegisterRequest;
import com.chatbot.chatbot_backend.entity.Client;
import com.chatbot.chatbot_backend.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;



   

   

    public Client register(RegisterRequest request) {
    if (clientRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already in use");   
    }

    Client client = new Client();
    client.setBusinessName(request.getBusinessName());
    client.setEmail(request.getEmail());
    client.setPassword(passwordEncoder.encode(request.getPassword()));
    client.setPhoneNumber(request.getPhoneNumber());
    return clientRepository.save(client);
    }



	// get client by id
    public Client getClientById(String id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    // get client by email
    public Client getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email).orElseThrow(() -> new RuntimeException("Client not found"));
    }



	public Client activate(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setActive(true);
        client.setActivatedAt(LocalDateTime.now());
        client.setApiKey(UUID.randomUUID().toString().replace("-", ""));

        return clientRepository.save(client);
    }

    // delete client
    public void deleteClient(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientRepository.delete(client);        
    }

    public ClientResponse toResponse(Client client) {
    ClientResponse response = new ClientResponse();
    response.setId(client.getId());
    response.setBusinessName(client.getBusinessName());
    response.setEmail(client.getEmail());
    response.setPhoneNumber(client.getPhoneNumber());
    response.setPlanType(client.getPlan());
    response.setActive(client.isActive());
    response.setApiKey(client.getApiKey());
    response.setCreatedAt(client.getCreatedAt());
    response.setActivatedAt(client.getActivatedAt());
    return response;
}

	}

