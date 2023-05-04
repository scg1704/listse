package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Vet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class VetService {
    private List<Vet> vets;

    /*
    * Como estamos hablando en este caso de mascotas, entonces vamos a ubicar los centro de atención
    * de la siguiente forma de acuerdo a su código:
    * 3 números en el código: Veterinaria de mascotas
    * 4 números en el código: Tienda de venta y cuidado de mascotas
    * 5 números en el código: Tienda de artículos de mascotas
    * Lo realicé así con el objetivo de tener variedad, y semejanza con los códigos de locación
    */
    public VetService(){
        vets = new ArrayList<>();
        vets.add(new Vet("1025", "Little's Pet Shop", "Cherry Pie Avenue"));
        vets.add(new Vet("10267", "Fabis Pet Care", "Lincoln Street"));
        vets.add(new Vet("20593", "Paws & Furs", "Bahamas AC, New Jersey"));
        vets.add(new Vet("234", "Boston Vet & Healthcare", "Grand Central Station"));
        vets.add(new Vet("3456", "Cares & Hugs Pet Shop", "Wade John St."));
        vets.add(new Vet("111", "Pets Health Services & Co", "Knicks Stadium, NY"));
    }

    /*MÉTODO PARA OBTENER VETERINARIAS SEGÚN EL TAMAÑO DEL CÓDIGO:
    Entrada:
    Tamaño del código
    Llamamos la lista de veterinarias
    Recorremos las veterinarias
        El código es del mismo largo?
        SI
            Se añade
    Se devuelven los datos de todas las veterinarias obtenidas
    */
    public List<Vet> getVetsByCodeSize(int size){
        List<Vet> listVets = new ArrayList<>();
        for(Vet vet: vets){
            if(vet.getCode().length()==size){
                listVets.add(vet);
            }
        }
        return listVets ;
    }

    /*MÉTODO PARA OBTENER VETERINARIA SEGÚN EL CÓDIGO:
    Entrada:
    Código
    Llamamos la lista de veterinarias
    Recorremos las veterinarias
        El código es igual?
        SI
            Se devuelve la veterinaria
    */
    public Vet getVetByCode(String code){
        for(Vet vet : vets){
            if(vet.getCode().equals(code)){
                return vet;
            }
        }
        return null;
    }
}
