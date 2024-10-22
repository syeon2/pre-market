package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;

public interface JpaItemRepository extends JpaRepository<ItemEntity, Long> {
}
