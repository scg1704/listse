package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Vet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportPetsVetGenderDTO {
    private List<VetGenderQuantityDTO> vetGenderQuantityDTOS;

    public ReportPetsVetGenderDTO(List<Vet> vets){
        vetGenderQuantityDTOS = new ArrayList<>();
        for (Vet vet: vets){
            vetGenderQuantityDTOS.add(new VetGenderQuantityDTO(vet.getName()));
        }
    }

    public void updateQuantityPets(String vet, char gender){
        for(VetGenderQuantityDTO ve:vetGenderQuantityDTOS){
            if(ve.getVet().equals(vet)){
                for(GenderQuantityDTO genderDTO: ve.getGenders()){
                    if(genderDTO.getGender()==gender){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        ve.setTotal(ve.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}
