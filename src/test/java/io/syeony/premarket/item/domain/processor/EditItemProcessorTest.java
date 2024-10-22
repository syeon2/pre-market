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
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;

class EditItemProcessorTest extends UnitTestSupport {

	@InjectMocks
	private EditItemProcessor editItemProcessor;

	@Mock
	private ItemRepository itemRepository = mock(ItemRepository.class);

	@Mock
	private ItemReader itemReader = mock(ItemReader.class);

	@BeforeEach
	void setUp() {
		editItemProcessor = new EditItemProcessor(itemRepository, itemReader);
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
		editItemProcessor.edit(itemId, memberId, item);

		// then
		verify(itemReader, times(1)).findByItemId(itemId);
		verify(itemRepository, times(1)).changeItemInfo(any(String.class), any(Item.class));
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
		assertThatThrownBy(() -> editItemProcessor.edit(itemId, memberId, item))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName(value = "Should throw InvalidCredentialsException when the member id is wrong")
	void edit_shouldThrowInvalidCredentialsException_whenMemberIdIsWrong() {
		// given
		String itemId = "itemId";
		String memberId = "memberId";
		Item item = createItemDomain("differentMemberId");

		given(itemReader.findByItemId(itemId)).willReturn(Optional.of(item));

		// when // then
		assertThatThrownBy(() -> editItemProcessor.edit(itemId, memberId, item))
			.isInstanceOf(InvalidCredentialsException.class);
	}

	private Item createItemDomain(String memberId) {
		return Item
			.builder()
			.memberId(new MemberId(memberId))
			.build();
	}
}
