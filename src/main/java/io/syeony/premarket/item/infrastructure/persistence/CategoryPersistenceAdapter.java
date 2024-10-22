package io.syeony.premarket.item.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.processor.reader.CategoryReader;
import io.syeony.premarket.item.domain.processor.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryRepository, CategoryReader {

	private final JpaCategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Override
	public void createCategory(Category category) {
		categoryRepository.save(categoryMapper.toEntity(category));
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public List<Category> retrieveAll() {
		return categoryRepository.findAll().stream()
			.map(categoryMapper::toDomain)
			.toList();
	}
}
