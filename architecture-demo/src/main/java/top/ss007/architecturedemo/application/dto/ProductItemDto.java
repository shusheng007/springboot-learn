package top.ss007.architecturedemo.application.dto;

import lombok.Getter;
import lombok.Setter;
import top.ss007.architecturedemo.domain.entity.Product;

@Setter
@Getter
public class ProductItemDto {
    private Integer count;
    private Product product;
}
