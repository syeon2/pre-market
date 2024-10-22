package io.syeony.premarket.item.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.JpaInfraTestSupport;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
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
		Item item = createItemDomain("memberId");

		// when
		Long savedItemId = itemPersistenceAdapter.register(item);

		// then
		assertThat(savedItemId).isInstanceOf(Long.class);
	}

	private Item createItemDomain(String memberId) {
		return Item.builder()
			.itemId("item_id")
			.name("itemA")
			.cost(new Cost(10000, 100))
			.stock(10)
			.introduction("hello")
			.itemType(ItemType.NORMAL_ORDER)
			.status(EntityStatus.ALIVE)
			.preOrderSchedule(null)
			.memberId(new MemberId(memberId))
			.build();
	}
}
