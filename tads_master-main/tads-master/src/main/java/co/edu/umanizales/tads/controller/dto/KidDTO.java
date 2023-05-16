package co.edu.umanizales.tads.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class KidDTO {

    //Le decimos que nos muestre un error dado ciertos casos
    @NotNull
    @NotBlank(message = "This field couldn't be empty")
    private String identification;

    //La entrada no puede ser mayor a 50 caracteres
    @NotNull
    @NotBlank(message = "This field couldn't be empty")
    @Size(min= 1, max = 50, message = "Name should be lower to 50 characters")
    private String name;

    //Entrada menor de int, y mayor
    @NotNull (message = "This field couldn't be empty")
    @Positive
    @Min(value = 1, message = "Minimum age should be higher than 0")
    @Max(value = 18, message = "Maximum age should be lower or equal than 18")
    private int age;

    @NotNull (message = "This field couldn't be empty")
    private char gender;

    @NotBlank(message = "This field couldn't be empty")
    private String codeCity;
}
