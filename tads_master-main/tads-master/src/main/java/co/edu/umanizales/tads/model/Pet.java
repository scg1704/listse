package co.edu.umanizales.tads.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    @NotBlank(message = "This field couldn't be empty")
    private String petCode;
    @NotBlank(message = "This field couldn't be empty")
    @Size(max = 20, message = "Name should be lower to 20 characters")
    private String petName;
    @NotBlank(message = "This field couldn't be empty")
    private String specie;
    @Min(value = 1)
    @Max(value = 30)
    private int petAge;
    @NotBlank(message = "This field couldn't be empty")
    private char gender;
    @NotBlank(message = "This field couldn't be empty")
    private Vet vet;
}
