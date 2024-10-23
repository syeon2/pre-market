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
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;

class EditItemProcessorTest extends UnitTestSupport {

	@InjectMocks
	private EditItemProcessor editItemProcessor;

	@Mock
	private ItemWriter itemWriter = mock(ItemWriter.class);

	@Mock
	private ItemReader itemReader = mock(ItemReader.class);

	@BeforeEach
	void setUp() {
		editItemProcessor = new EditItemProcessor(itemWriter, itemReader);
	}

	@Test
	@DisplayName(value = "Edit item successfully when the item id and member id are valid")
	void edit() {
		// given
		String itemId = "itemId";
		String memberId = "memberId";
		Item item = createItemDomain(memberId);

		given(itemReader.findByItemId(itemId)).willReturn(Optional.of(item));

		// when
		editItemProcessor.edit(itemId, item);

		// then
		verify(itemReader, times(1)).findByItemId(itemId);
		verify(itemWriter, times(1)).changeItemInfo(any(String.class), any(Item.class));
	}

	@Test
	@DisplayName(value = "Should throw IllegalArgumentException when the item id is wrong")
	void edit_shouldThrowIllegalArgumentException_whenItemIdIsWrong() {
		// given
		String itemId = "itemId";
		String memberId = "memberId";
		Item item = createItemDomain(memberId);

		given(itemReader.findByItemId(itemId)).willReturn(Optional.empty());

		// when // then
		assertThatThrownBy(() -> editItemProcessor.edit(itemId, item))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName(value = "Should throw InvalidCredentialsException when the member id is wrong")
	void edit_shouldThrowInvalidCredentialsException_whenMemberIdIsWrong() {
		// given
		String itemId = "itemId";
		Item item = createItemDomain("memberId");
		Item diiferItem = createItemDomain("differentMemberId");

		given(itemReader.findByItemId(itemId)).willReturn(Optional.of(item));

		// when // then
		assertThatThrownBy(() -> editItemProcessor.edit(itemId, diiferItem))
			.isInstanceOf(InvalidCredentialsException.class);
	}

	private Item createItemDomain(String sellerId) {
		return Item
			.builder()
			.seller(Seller.initId(sellerId))
			.build();
	}
}
