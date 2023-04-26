package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportKidsLocationGenderDTO {
    private List<LocationGenderQuantityDTO> locationGenderQuantityDTOS;

    public ReportKidsLocationGenderDTO(List<City> cities){
        locationGenderQuantityDTOS = new ArrayList<>();
        for (City city: cities){
            locationGenderQuantityDTOS.add(new LocationGenderQuantityDTO(city.getName()));
        }
    }

    //Método actualizar
    public void updateQuantity(String city, char gender){
        for(LocationGenderQuantityDTO cit:locationGenderQuantityDTOS){
            if(cit.getCity().equals(city)){
                for(GenderQuantityDTO genderDTO: cit.getGenders()){
                    if(genderDTO.getGender()==gender){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        cit.setTotal(cit.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}
