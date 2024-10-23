package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.infrastructure.persistence.entity.CategoryEntity;

@Component
public class CategoryMapper {

	public CategoryEntity toEntity(Category category) {
		return CategoryEntity.builder()
			.name(category.getName())
			.build();
	}

	public Category toDomain(CategoryEntity entity) {
		return Category.builder()
			.no(entity.getId())
			.name(entity.getName())
			.build();
	}
}
