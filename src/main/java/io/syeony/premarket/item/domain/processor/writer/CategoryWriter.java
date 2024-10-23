package io.syeony.premarket.item.domain.processor.writer;

import io.syeony.premarket.item.domain.model.Category;

public interface CategoryWriter {

	void createCategory(Category category);

	void deleteCategory(Long categoryId);
}
