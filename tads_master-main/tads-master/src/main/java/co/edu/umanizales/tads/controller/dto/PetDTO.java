package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Specie;
import co.edu.umanizales.tads.model.Vet;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PetDTO {
    //Le decimos que nos muestre un error dado ciertos casos
    @NotBlank(message = "This field couldn't be empty")
    private String petCode;
    @NotBlank(message = "This field couldn't be empty")
    @Size(min = 1, max = 20, message = "Name should be lower to 20 characters")
    private String petName;
    @NotBlank(message = "This field couldn't be empty")
    private Specie specie;
    @NotNull(message = "This field couldn't be empty")
    @Positive
    @Min(value = 1)
    @Max(value = 30)
    private int petAge;
    @NotNull(message = "This field couldn't be empty")
    private char gender;
    @NotBlank(message = "This field couldn't be empty")
    private String codeVet;
    @NotBlank(message = "This field couldn't be empty")
    private boolean shower;
}
