package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VetGenderQuantityDTO {
    private String vet;
    private List<GenderQuantityDTO> genders;
    private int total;

    public VetGenderQuantityDTO(String vet) {
        this.vet = vet;
        this.total=0;
        this.genders = new ArrayList<>();
        this.genders.add(new GenderQuantityDTO('M',0));
        this.genders.add(new GenderQuantityDTO('F',0));
    }
}
