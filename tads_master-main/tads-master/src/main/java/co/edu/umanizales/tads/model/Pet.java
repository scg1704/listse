package co.edu.umanizales.tads.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    @NotNull
    @NotBlank(message = "This field couldn't be empty")
    private String petCode;
    @NotNull
    @NotBlank(message = "This field couldn't be empty")
    @Size(min = 1, max = 20, message = "Name should be lower to 20 characters")
    private String petName;
    @NotBlank(message = "This field couldn't be empty")
    private Specie specie;
    @NotNull(message = "This field couldn't be empty")
    @Positive
    @Min(value = 1, message = "Minimum age should be higher than 0")
    @Max(value = 30, message = "Maximum age should be lower or higher than 30")
    private int petAge;
    @NotNull(message = "This field couldn't be empty")
    private char gender;
    @NotNull(message = "This field couldn't be empty")
    private Vet vet;
    @NotNull(message = "This field couldn't be empty")
    private boolean shower;
}
