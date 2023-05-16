package co.edu.umanizales.tads.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vet {
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String address;
}
