package co.edu.umanizales.tads.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Kid {
    @NotNull
    @NotBlank(message = "This field couldn't be empty")
    private String identification;
    @NotNull
    @NotBlank(message = "This field couldn't be empty")
    @Size(min = 1, max = 50, message = "Name should be lower to 50 characters")
    private String name;

    @NotNull(message = "This field couldn't be empty")
    @Positive
    @Min(value = 1, message = "Minimum age should be higher than 0")
    @Max(value = 18, message = "Maximum age should be lower or equal than 18")
    private int age;
    @NotNull(message = "This field couldn't be empty")
    private char gender;
    @NotNull(message = "This field couldn't be empty")
    private City city;
}
