package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Gender;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class GenderService {
    private List<Gender> genders;
    public GenderService(){
    }
}
