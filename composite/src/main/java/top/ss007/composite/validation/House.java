package top.ss007.composite.validation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class House {

    @AssertTrue
    private Boolean isVilla;

    @DecimalMin("100000000")
    private Integer price;
}
