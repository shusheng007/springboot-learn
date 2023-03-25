package top.shusheng007.composite.validation;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;

@Data
public class House {

    @AssertTrue
    private Boolean isVilla;

    @DecimalMin("100000000")
    private Integer price;
}
