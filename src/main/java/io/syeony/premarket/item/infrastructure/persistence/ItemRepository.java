package io.syeony.premarket.item.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	Optional<ItemEntity> findByItemId(String itemId);
}
