package top.shusheng007.architecturedemo.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private String productId;
    private int quantity;
    private double price;

    public double getTotalPrice() {
        return quantity * price;
    }
}
