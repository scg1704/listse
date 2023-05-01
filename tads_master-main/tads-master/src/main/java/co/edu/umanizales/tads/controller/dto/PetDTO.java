package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Vet;
import lombok.Data;

@Data
public class PetDTO {
    private String petCode;
    private String petName;
    private String specie;
    private int petAge;
    private char gender;
    private String codeVet;
}
