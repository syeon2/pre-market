package io.syeony.premarket.item.domain.processor.writer;

import io.syeony.premarket.item.domain.model.Category;

public interface CategoryWriter {

	void create(Category category);

	void delete(Long categoryNo);
}
