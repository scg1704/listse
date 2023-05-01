package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Vet;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetsVetDTO {
    private Vet vet;
    private int quantity;
}
