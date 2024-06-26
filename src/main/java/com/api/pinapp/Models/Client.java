package com.api.pinapp.Models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "clientes")
public class Client {
    @Id
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @NotNull(message = "Age Name cannot be null")
    private int age;
    @NotNull(message = "Bith date Name cannot be null")
    private LocalDate birthDate;
    private LocalDate lifeExpectancy;
}
