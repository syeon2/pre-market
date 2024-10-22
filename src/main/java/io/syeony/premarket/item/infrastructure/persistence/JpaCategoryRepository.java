package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.item.infrastructure.persistence.entity.CategoryEntity;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
