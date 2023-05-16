package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.City;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CityService {
    private List<City> cities;

    public CityService() {
        cities = new ArrayList<>();
        cities.add(new City("169", "Colombia"));
        cities.add(new City("16905", "Antioquia"));
        cities.add(new City("16917", "Caldas"));
        cities.add(new City("16963", "Risaralda"));
        cities.add(new City("16905001", "Medellin"));
        cities.add(new City("16963001", "Pereira"));
        cities.add(new City("16917001", "Manizales"));
        cities.add(new City("16917003", "Chinchiná"));
    }

    /*MÉTODO PARA OBTENER LOCALIZACIONES SEGÚN EL TAMAÑO DEL CÓDIGO:
    Entrada:
    Tamaño del código
    Llamamos la lista de ciudades
    Recorremos las ciudades
        El código es del mismo largo?
        SI
            Se añade
    Se devuelven los datos de todas las localizaciones obtenidas
    */
    public List<City> getCitiesByCodeSize(int size){
        List<City> listCities = new ArrayList<>();
        for(City city: cities){
            if(city.getCode().length()==size){
                listCities.add(city);
            }
        }
        return listCities;
    }

    /*MÉTODO PARA OBTENER CIUDAD SEGÚN EL CÓDIGO:
    Entrada:
    Código
    Llamamos la lista de ciudades
    Recorremos las ciudades
        El código es igual?
        SI
            Se devuelve la ciudad
    */
    public City getCityByCode(String code){
        for(City city : cities){
            if(city.getCode().equals(code)){
                return city;
            }
        }
        return null;
    }
}
