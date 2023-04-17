package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Kid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidsCityDTO {
    private City city;
    private int quantity;
}
