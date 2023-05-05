package co.edu.umanizales.tads.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class KidDTO {

    //Le decimos que nos muestre un error dado ciertos casos
    @NotBlank(message = "This field couldn't be empty")
    private String identification;

    //La entrada no puede ser mayor a 50 caracteres
    @NotBlank(message = "This field couldn't be empty")
    @Size(max = 50, message = "Name should be lower to 50 characters")
    private String name;

    //Entrada menor de int, y mayor
    @Min(value = 1)
    @Max(value = 18)
    private int age;

    @NotBlank(message = "This field couldn't be empty")
    private char gender;

    @NotBlank(message = "This field couldn't be empty")
    private String codeCity;
}
