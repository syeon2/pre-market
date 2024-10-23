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
	private CategoryRepository categoryRepository;

	@Test
	@DisplayName(value = "Create Category")
	void create() {
		// given
		String categoryName = "categoryA";
		Category domain = Category.initializeForCreate(categoryName);

		// when
		categoryPersistenceAdapter.create(domain);

		// then
		List<CategoryEntity> findCategories = categoryRepository.findAll();
		assertThat(findCategories).hasSize(1);
		assertThat(findCategories.get(0).getName()).isEqualTo(categoryName);
	}

	@Test
	@DisplayName(value = "Delete Category")
	void delete() {
		// given
		List<CategoryEntity> entityList = List.of(createEntity());
		categoryRepository.saveAll(entityList);

		List<CategoryEntity> findCategories = categoryRepository.findAll();
		assertThat(findCategories).hasSize(entityList.size());

		// when
		findCategories.forEach(entity -> categoryPersistenceAdapter.delete(entity.getId()));

		// then
		assertThat(categoryRepository.findAll()).hasSize(0);
	}

	@Test
	@DisplayName(value = "Retrieve all categories")
	void retrieveAll() {
		// given
		List<CategoryEntity> entityList = List.of(createEntity(), createEntity());
		categoryRepository.saveAll(entityList);

		// when
		List<Category> categories = categoryPersistenceAdapter.retrieveAll();

		// then
		assertThat(categories).hasSize(entityList.size());
	}

	private CategoryEntity createEntity() {
		return CategoryEntity.of(null, "categoryName");
	}
}
