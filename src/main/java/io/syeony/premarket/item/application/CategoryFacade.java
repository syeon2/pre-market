package io.syeony.premarket.item.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.processor.reader.CategoryReader;
import io.syeony.premarket.item.domain.processor.writer.CategoryWriter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryFacade {

	private final CategoryWriter categoryWriter;
	private final CategoryReader categoryReader;

	@Transactional
	public void createCategory(final String name) {
		categoryWriter.create(Category.initializeForCreate(name));
	}

	@Transactional
	public void deleteCategory(final Long categoryNo) {
		categoryWriter.delete(categoryNo);
	}

	@Transactional(readOnly = true)
	public List<Category> retrieveAllCategories() {
		return categoryReader.retrieveAll();
	}
}
