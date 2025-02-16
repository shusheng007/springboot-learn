package top.ss007.architecturedemo.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import top.ss007.architecturedemo.domain.aggregate.Inventory;
import top.ss007.architecturedemo.domain.external.repository.InventoryRepository;

import java.util.Optional;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {
    @Override
    public Optional<Inventory> findById(String inventoryId) {
        Inventory inventory = new Inventory();
        inventory.setProductId("123");
        inventory.setProductAmount(50);
        return Optional.of(inventory);
    }

    @Override
    public Inventory save(Inventory inventory) {
        return null;
    }
}
