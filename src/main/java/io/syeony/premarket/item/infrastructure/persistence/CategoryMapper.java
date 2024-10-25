package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.infrastructure.persistence.entity.CategoryEntity;

@Component
public class CategoryMapper {

	public CategoryEntity toEntity(Category category) {
		return CategoryEntity.of(null, category.getName());
	}

	public Category toDomain(CategoryEntity entity) {
		return Category.of(entity.getId(), entity.getName());
	}
}
