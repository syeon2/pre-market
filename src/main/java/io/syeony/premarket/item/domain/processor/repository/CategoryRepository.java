package io.syeony.premarket.item.domain.processor.repository;

import io.syeony.premarket.item.domain.model.Category;

public interface CategoryRepository {

	void createCategory(Category category);

	void deleteCategory(Long categoryId);
}
