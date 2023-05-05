package co.edu.umanizales.tads.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Kid {
    @NotBlank(message = "This field couldn't be empty")
    private String identification;
    @NotBlank(message = "This field couldn't be empty")
    @Size(max = 50, message = "Name should be lower to 50 characters")
    private String name;

    @Min(value = 1)
    @Max(value = 18)
    private int age;
    @NotBlank(message = "This field couldn't be empty")
    private char gender;
    @NotBlank(message = "This field couldn't be empty")
    private City city;
}
