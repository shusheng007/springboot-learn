package top.ss007.architecturedemo.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;
    private String type;
    private double price;
}
