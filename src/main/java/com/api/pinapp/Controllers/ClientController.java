package com.api.pinapp.Controllers;

import com.api.pinapp.Models.Client;
import com.api.pinapp.Models.ClientKPI;
import com.api.pinapp.Services.ClientService;
import com.api.pinapp.Services.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "Client Management API", description = "Client Management API")
public class ClientController {

    @Autowired
    private ClientService clientService;
    
    @Autowired
    private StatisticService statisticService;

    @PostMapping("/create")
    @Operation(summary = "Create a new client", description = "Create a new client")
    public ResponseEntity createClient(@RequestBody @Validated Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Client savedClient = clientService.saveClient(client);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientsKPI")
    @Operation(summary = "Get KPI", description = "Get the average and standard deviation of client ages")
    public ResponseEntity getAverageAge() {
        try {
            double averageAge = statisticService.getAverageAge();
            double standardDeviation = statisticService.getStandardDeviationAge();
            ClientKPI response = new ClientKPI();
            response.setAverageAge(averageAge);
            response.setStandardDeviation(standardDeviation);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error calculating KPI", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listAll")
    @Operation(summary = "List Clients", description = "List Clients")
    public ResponseEntity<List<Client>> listClients() {
        try {
            List<Client> clients = clientService.getAllClients();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}