package top.shusheng007.architecturedemo.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Inventory extends RootAggregate<Object> {

    private String productId;
    private Integer productAmount;

    public void decreaseStock(Integer amount) {
        if (productAmount < amount) {
            throw new IllegalStateException("no stock.");
        }
        productAmount = productAmount - amount;
    }

}
