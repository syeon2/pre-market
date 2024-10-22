package io.syeony.premarket.item.domain.processor.reader;

import java.util.List;

import io.syeony.premarket.item.domain.model.Category;

public interface CategoryReader {

	List<Category> retrieveAll();
}
