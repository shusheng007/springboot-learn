package top.ss007.architecturedemo.domain.external.repository;

import top.ss007.architecturedemo.domain.aggregate.Inventory;

import java.util.Optional;

public interface InventoryRepository {
    Optional<Inventory> findById(String inventoryId);

    Inventory save(Inventory inventory);
}
