package io.syeony.premarket.item.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.JpaInfraTestSupport;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
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
	private JpaItemRepository itemRepository;

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

	private Item createDeactivateItemDomain(String memberId, String itemId) {
		return Item.builder()
			.itemId(itemId)
			.status(EntityStatus.DELETED)
			.memberId(new MemberId(memberId))
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
			.memberId(new MemberId("memberId"))
			.status(EntityStatus.ALIVE)
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