package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.AgeRange;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgeRangeKidsDTO {
    private AgeRange range;
    int quantity;
}
