package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private String petCode;
    private String petName;
    private int petAge;
    private char gender;
    private Vet vet;
}
