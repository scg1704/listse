package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Gender;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class GenderService {
    private List<Gender> genders;
    public GenderService(){
        genders = new ArrayList<>();
        genders.add(new Gender("1", 'M'));
        genders.add(new Gender("2", 'F'));
    }

    public Gender getGenderByCode(String codeGender){
        for(Gender gender : genders){
            if(gender.getCodeGender().equals(codeGender)){
                return gender;
            }
        }
        return null;
    }
}
