package io.syeony.premarket.item.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.JpaInfraTestSupport;
import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.infrastructure.persistence.entity.CategoryEntity;

@Import({
	CategoryPersistenceAdapter.class,
	CategoryMapper.class,
})
class CategoryPersistenceAdapterTest extends JpaInfraTestSupport {

	@Autowired
	private CategoryPersistenceAdapter categoryPersistenceAdapter;

	@Autowired
	private JpaCategoryRepository jpaCategoryRepository;

	@Test
	@DisplayName(value = "Create Category")
	void createCategory() {
		// given
		String categoryName = "categoryA";
		Category domain = createCategoryDomain(categoryName);

		// when
		categoryPersistenceAdapter.createCategory(domain);

		// then
		List<CategoryEntity> findCategories = jpaCategoryRepository.findAll();
		assertThat(findCategories).hasSize(1);
		assertThat(findCategories.get(0).getName()).isEqualTo(categoryName);
	}

	@Test
	@DisplayName(value = "Delete Category")
	void deleteCategory() {
		// given
		List<CategoryEntity> entityList = List.of(createCategoryEntity());
		jpaCategoryRepository.saveAll(entityList);

		List<CategoryEntity> findCategories = jpaCategoryRepository.findAll();
		assertThat(findCategories).hasSize(entityList.size());

		// when
		findCategories.forEach(entity -> categoryPersistenceAdapter.deleteCategory(entity.getId()));

		// then
		assertThat(jpaCategoryRepository.findAll()).hasSize(0);
	}

	@Test
	@DisplayName(value = "Retrieve all categories")
	void retrieveAll() {
		// given
		List<CategoryEntity> entityList = List.of(createCategoryEntity(), createCategoryEntity());
		jpaCategoryRepository.saveAll(entityList);

		// when
		List<Category> categories = categoryPersistenceAdapter.retrieveAll();

		// then
		assertThat(categories).hasSize(entityList.size());
	}

	private Category createCategoryDomain(String categoryName) {
		return Category.builder()
			.name(categoryName)
			.build();
	}

	private CategoryEntity createCategoryEntity() {
		return CategoryEntity.builder()
			.name("categoryName")
			.build();
	}
}
