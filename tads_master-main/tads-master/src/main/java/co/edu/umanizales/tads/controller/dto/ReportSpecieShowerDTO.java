package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.ListDECircular;
import co.edu.umanizales.tads.model.Specie;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportSpecieShowerDTO {
    private List<ShowerQuantityDTO> showerQuantityDTOS;
    public ReportSpecieShowerDTO(List<Specie> species){
        showerQuantityDTOS = new ArrayList<>();
        for (Specie specie: species){
            showerQuantityDTOS.add(new ShowerQuantityDTO(specie.getName(), 0));
        }
    }

    /*
    Entrada:
    La especie que tenemos, de esta forma podemos encontrar la cantidad que hay de ella
    Recorremos la lista de cantidad de especies
        Tiene la misma especie?
            Se suma a la cantidad
    No está la especie, se dice que no hay, que es 0
     */

    public int getQuantityBySpecie(String specie) {
        for (ShowerQuantityDTO shower : showerQuantityDTOS) {
            if (shower.getSpecie().equals(specie)) {
                return shower.getQuantity();
            }
        }
        return 0;
    }

    /*
    Entrada:
    Tomamos la especie que poseemos y la cantidad que esta posee
    Recorremos la lista de las especies
        Si tenemos esa misma especie
            Entonces decimos que se añade la cantidad que tenemos y se le suma 1
            Organizamos la nueva cantidad como la cantidad que se posee
     */

    public void updateQuantitySpecies(String specie, int quantity){
        for(ShowerQuantityDTO spec:showerQuantityDTOS){
            if (spec.getSpecie().equals(specie)){
                int updatedQuantity = spec.getQuantity() + quantity;
                spec.setQuantity(quantity);
                break;
            }
        }
    }
}
