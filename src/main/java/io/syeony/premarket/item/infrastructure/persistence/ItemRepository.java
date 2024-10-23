package io.syeony.premarket.item.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.item.infrastructure.persistence.custom.ItemRepositoryCustom;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>, ItemRepositoryCustom {

	Optional<ItemEntity> findByItemId(String itemId);
}
