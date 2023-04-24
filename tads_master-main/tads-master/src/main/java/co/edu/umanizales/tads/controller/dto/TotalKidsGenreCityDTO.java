package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TotalKidsGenreCityDTO {
    private City cities;
    private List<KidsGenreCityDTO> genders;
    private int quantity;
}
