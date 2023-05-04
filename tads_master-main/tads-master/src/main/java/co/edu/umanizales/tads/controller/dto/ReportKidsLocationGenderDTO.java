package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportKidsLocationGenderDTO {
    private List<LocationGenderQuantityDTO> locationGenderQuantityDTOS;

    /*MÉTODO PARA OBTENER CIUDAD SEGÚN EL CÓDIGO:
    Entrada:
    La lista de ciudades
    Creamos una nueva lista con los arreglos
    Recorremos todas las ciudades
        Añadimos el reporte con cantidad de niños, género, y localización
    */
    public ReportKidsLocationGenderDTO(List<City> cities){
        locationGenderQuantityDTOS = new ArrayList<>();
        for (City city: cities){
            locationGenderQuantityDTOS.add(new LocationGenderQuantityDTO(city.getName()));
        }
    }

    /*MÉTODO PARA ACTUALIZAR:
    Entrada:
    Ciudad, género
    Recorremos todas las localizaciones y géneros
        Tiene la misma ciudad?
        SI
            Obtenemos el número de niños por ciudad según su género
                El género es el mismo?
                SI
                  Sumamos cantidad al género, y al total de la ciudad
    */
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
