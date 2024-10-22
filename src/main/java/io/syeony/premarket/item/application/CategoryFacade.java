package io.syeony.premarket.item.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.processor.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryFacade {

	private final CategoryRepository categoryRepository;

	@Transactional
	public void createCategory(final String name) {
		categoryRepository.createCategory(Category.initialize(name));
	}

	@Transactional
	public void deleteCategory(final Long categoryId) {
		categoryRepository.deleteCategory(categoryId);
	}
}
