package io.syeony.premarket.item.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.processor.reader.CategoryReader;
import io.syeony.premarket.item.domain.processor.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryFacade {

	private final CategoryRepository categoryRepository;
	private final CategoryReader categoryReader;

	@Transactional
	public void createCategory(final String name) {
		categoryRepository.createCategory(Category.initialize(name));
	}

	@Transactional
	public void deleteCategory(final Long categoryId) {
		categoryRepository.deleteCategory(categoryId);
	}

	@Transactional(readOnly = true)
	public List<Category> retrieveAllCategories() {
		return categoryReader.retrieveAll();
	}
}
