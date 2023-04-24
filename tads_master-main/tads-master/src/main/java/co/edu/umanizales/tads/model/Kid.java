package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Kid {
    private String identification;
    private String name;
    private int age;
    private Gender gender;
    private City city;
}
