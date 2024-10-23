package io.syeony.premarket.item.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.JpaInfraTestSupport;
import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;
import io.syeony.premarket.support.common.EntityStatus;

@Import({
	ItemPersistenceAdapter.class,
	ItemMapper.class,
})
class ItemPersistenceAdapterTest extends JpaInfraTestSupport {

	@Autowired
	private ItemPersistenceAdapter itemPersistenceAdapter;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	@DisplayName(value = "Save item and return item id")
	void register() {
		// given
		Item item = createItemDomain();

		// when
		Long savedItemId = itemPersistenceAdapter.register(item);

		// then
		assertThat(savedItemId).isInstanceOf(Long.class);
	}

	@Test
	@DisplayName(value = "Change item status to delete")
	void deactivate() {
		// given
		String memberId = "memberId";
		String itemId = "itemId";
		ItemEntity item = createItemEntity(memberId, itemId);
		ItemEntity savedItem = itemRepository.save(item);

		// when
		itemPersistenceAdapter.deactivate(createDeactivateItemDomain(memberId, itemId));

		// then
		Optional<ItemEntity> findItemOptional = itemRepository.findById(savedItem.getId());
		assertThat(findItemOptional.isPresent()).isTrue();
		assertThat(findItemOptional.get().getStatus()).isEqualTo(EntityStatus.DELETED);
	}

	@Test
	@DisplayName(value = "Change item information")
	void changeItemInformation() {
		// given
		String itemId = "itemId";
		ItemEntity entity = createItemEntity("memberId", itemId);
		itemRepository.save(entity);

		String changeName = "changeName";
		int changePrice = 20000;
		int changeDiscount = 200;
		int changeStock = 20;
		String changeIntroduction = "changeIntroduction";
		long changeCategoryId = 2L;
		Item editItem = createEditItemDomain(changeName, changePrice, changeDiscount, changeStock,
			changeIntroduction, changeCategoryId);

		// when
		itemPersistenceAdapter.changeItemInfo(itemId, editItem);

		// then
		List<ItemEntity> findAll = itemRepository.findAll();
		assertThat(findAll)
			.extracting("name", "price", "discount", "stock", "introduction", "categoryId")
			.containsExactlyInAnyOrder(
				tuple(changeName, changePrice, changeDiscount, changeStock, changeIntroduction, changeCategoryId)
			);
	}

	@Test
	@DisplayName(value = "Find item by item id")
	void findByItemId() {
		// given
		String itemId = "itemId";
		ItemEntity entity = createItemEntity("memberId", itemId);
		itemRepository.save(entity);

		// when
		Optional<Item> findItemOptional = itemPersistenceAdapter.findByItemId(itemId);

		// then
		assertThat(findItemOptional.isPresent()).isTrue();
		assertThat(findItemOptional.get().getItemId()).isEqualTo(itemId);
	}

	private Item createEditItemDomain(String changeName, int changePrice, int changeDiscount, int changeStock,
		String changeIntroduction, long changeCategoryId) {
		return Item.builder()
			.name(changeName)
			.cost(new Cost(changePrice, changeDiscount))
			.stock(changeStock)
			.introduction(changeIntroduction)
			.category(Category.builder().id(changeCategoryId).build())
			.build();
	}

	private Item createDeactivateItemDomain(String memberId, String itemId) {
		return Item.builder()
			.itemId(itemId)
			.status(EntityStatus.DELETED)
			.seller(Seller.builder().memberId(memberId).build())
			.build();
	}

	private Item createItemDomain() {
		return Item.builder()
			.itemId("itemId")
			.name("itemA")
			.cost(new Cost(10000, 100))
			.stock(10)
			.introduction("hello")
			.itemType(ItemType.NORMAL_ORDER)
			.preOrderSchedule(null)
			.seller(Seller.builder().memberId("memberId").build())
			.status(EntityStatus.ALIVE)
			.category(Category.builder().id(1L).build())
			.build();
	}

	private ItemEntity createItemEntity(String memberId, String itemId) {
		return ItemEntity.builder()
			.itemId(itemId)
			.name("itemA")
			.price(10000)
			.discount(100)
			.stock(10)
			.introduction("hello")
			.type(ItemType.NORMAL_ORDER)
			.status(EntityStatus.ALIVE)
			.preOrderSchedule(null)
			.categoryId(1L)
			.memberId(memberId)
			.build();
	}
}
