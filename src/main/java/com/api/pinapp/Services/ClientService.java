package com.api.pinapp.Services;

import com.api.pinapp.Models.Client;
import com.api.pinapp.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clienteRepository;

    public Client saveClient(Client client) {
        return clienteRepository.save(client);
    }
    
    @Cacheable(value = "clients")
    public List<Client> getAllClients() {
        List<Client> clients = clienteRepository.findAll();
        clients.forEach(client -> calculateProbableDeathDate(client));
        return clients;
    }
    

    public Client calculateProbableDeathDate(Client client) {
        int lifeExpectancy = 80;
        LocalDate probableDeathDate = client.getBirthDate().plusYears(lifeExpectancy);
        client.setLifeExpectancy(probableDeathDate);
        return client;
    }
}