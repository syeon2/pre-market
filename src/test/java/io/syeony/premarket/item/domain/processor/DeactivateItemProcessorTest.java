package io.syeony.premarket.item.domain.processor;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;

class DeactivateItemProcessorTest extends UnitTestSupport {

	@InjectMocks
	private DeactivateItemProcessor processor;

	@Mock
	private ItemReader itemReader = mock(ItemReader.class);

	@Mock
	private ItemWriter itemWriter = mock(ItemWriter.class);

	@BeforeEach
	void setUp() {
		processor = new DeactivateItemProcessor(itemReader, itemWriter);
	}

	@Test
	@DisplayName(value = "Change item state to delete")
	void deactivate() {
		// given
		String memberId = "memberId";
		String itemId = "itemId";
		Item item = createItemDomain(memberId, itemId);

		given(itemReader.findByItemId(itemId)).willReturn(Optional.of(item));

		// when
		processor.deactivate(memberId, itemId);

		// then
		verify(itemReader, times(1)).findByItemId(itemId);
		verify(itemWriter, times(1)).deactivate(any(Item.class));
	}

	@Test
	@DisplayName(value = "Should throw IllegalArgumentException when the item id is not valid")
	void deactivate_shouldThrowIllegalArgumentException() {
		// given
		String memberId = "memberId";
		String itemId = "itemId";

		given(itemReader.findByItemId(itemId)).willReturn(Optional.empty());

		// when // then
		assertThatThrownBy(() -> processor.deactivate(memberId, itemId))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName(value = "Should throw InvalidCredentialException when the member id is not valid")
	void deactivate_shouldThrowInvalidCredentialException() {
		// given
		String memberId = "memberId";
		String itemId = "itemId";
		Item item = createItemDomain("differentMemberId", itemId);

		given(itemReader.findByItemId(itemId)).willReturn(Optional.of(item));

		// when // then
		assertThatThrownBy(() -> processor.deactivate(memberId, itemId))
			.isInstanceOf(InvalidCredentialsException.class);
	}

	private Item createItemDomain(String memberId, String itemId) {
		return Item.builder()
			.itemId(itemId)
			.itemName("itemA")
			.cost(new Cost(10000, 100))
			.stock(10)
			.introduction("hello")
			.itemType(ItemType.NORMAL_ORDER)
			.preOrderSchedule(null)
			.seller(Seller.builder().memberId(memberId).build())
			.build();
	}
}
