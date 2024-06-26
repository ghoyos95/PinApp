package com.api.pinapp.Services;

import com.api.pinapp.Models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    
    @Autowired
    private ClientService clientService;
    
    public double getAverageAge() {
        List<Client> clients = clientService.getAllClients();
        return clients.stream().mapToInt(Client::getAge).average().orElse(0);
    }

    public double getStandardDeviationAge() {
        List<Client> clients = clientService.getAllClients();
        double average = getAverageAge();
        return Math.sqrt(clients.stream().mapToDouble(c -> Math.pow(c.getAge() - average, 2)).average().orElse(0));
    }
}
