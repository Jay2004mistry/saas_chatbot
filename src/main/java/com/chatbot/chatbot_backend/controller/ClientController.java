package com.chatbot.chatbot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.chatbot_backend.dto.ClientResponse;
import com.chatbot.chatbot_backend.dto.RegisterRequest;
import com.chatbot.chatbot_backend.entity.Client;
import com.chatbot.chatbot_backend.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@RequestMapping("/api/clients")
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    

    @PostMapping("/register")
    public ResponseEntity<ClientResponse> register(@Valid @RequestBody RegisterRequest request) {
        Client client = clientService.register(request);
        return ResponseEntity.ok(clientService.toResponse(client));
    }
    

    // get client by id
  @GetMapping("/{id}")
public ResponseEntity<ClientResponse> getClient(@PathVariable String id) {
    Client client = clientService.getClientById(id);
    return ResponseEntity.ok(clientService.toResponse(client));
}

    // get client by email
   @GetMapping("/email")
public ResponseEntity<ClientResponse> getClientByEmail(@RequestParam String email) {
    Client client = clientService.getClientByEmail(email);
    return ResponseEntity.ok(clientService.toResponse(client));
}

    // update client details
    @PutMapping("/{id}/activate")
    public ResponseEntity<ClientResponse> activate(@PathVariable String id) {
        Client client = clientService.activate(id);
        return ResponseEntity.ok(clientService.toResponse(client));
    }

    // delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
