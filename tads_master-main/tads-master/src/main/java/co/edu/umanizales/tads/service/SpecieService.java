package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Specie;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class SpecieService {
    private List<Specie> species;

    public SpecieService(){
        species = new ArrayList<>();
        species.add(new Specie("100", "Perro"));
        species.add(new Specie("101", "Gato"));
    }

    public Specie getSpecieByCode(String code){
        for (Specie specie: species){
            if (specie.getCode().equals(code)){
                return specie;
            }
        }
        return null;
    }
}
