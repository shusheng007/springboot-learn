package top.ss007.architecturedemo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderCreateDto {
    private List<ProductItemDto> products;
}
