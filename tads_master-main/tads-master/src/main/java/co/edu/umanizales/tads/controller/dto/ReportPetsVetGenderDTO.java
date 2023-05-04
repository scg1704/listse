package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Vet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportPetsVetGenderDTO {
    private List<VetGenderQuantityDTO> vetGenderQuantityDTOS;

    /*MÉTODO PARA OBTENER VETERINARIA SEGÚN EL CÓDIGO:
    Entrada:
    La lista de veterinarias
    Creamos una nueva lista con los arreglos
    Recorremos todas las veterinarias
        Añadimos el reporte con cantidad de mascotas, género, y veterinaria
    */
    public ReportPetsVetGenderDTO(List<Vet> vets){
        vetGenderQuantityDTOS = new ArrayList<>();
        for (Vet vet: vets){
            vetGenderQuantityDTOS.add(new VetGenderQuantityDTO(vet.getName()));
        }
    }

    /*MÉTODO PARA ACTUALIZAR:
    Entrada:
    Veterinaria, género
    Recorremos todas las veterinarias y géneros
        Tiene la misma veterinaria?
        SI
            Obtenemos el número de mascotas por veterinaria según su género
                El género es el mismo?
                SI
                  Sumamos cantidad al género, y al total de la veterinaria
    */
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
