package io.syeony.premarket.item.domain.processor;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;

class RegisterItemProcessorTest extends UnitTestSupport {

	@InjectMocks
	private RegisterItemProcessor registerItemProcessor;

	@Mock
	private ItemWriter itemWriter = mock(ItemWriter.class);

	@Mock
	private AccountReader accountReader = mock(AccountReader.class);

	@BeforeEach
	void setUp() {
		registerItemProcessor = new RegisterItemProcessor(itemWriter, accountReader);
	}

	@Test
	@DisplayName(value = "Register Item successfully when the member id is valid")
	void registerItem_shouldReturnItemId_whenMemberIdIsValid() {
		// given
		String memberId = "memberId";
		Item item = createItemDomain(memberId);

		Long itemId = 1L;
		given(accountReader.existsByMemberId(memberId)).willReturn(true);
		given(itemWriter.register(any(Item.class))).willReturn(itemId);

		// when
		Long savedItemId = registerItemProcessor.registerItem(item);

		// then
		verify(accountReader, times(1)).existsByMemberId(memberId);
		verify(itemWriter, times(1)).register(any(Item.class));
		assertThat(itemId).isEqualTo(savedItemId);
	}

	@Test
	@DisplayName(value = "Should throw InvalidCredentialException when the member is not found")
	void registerItem_shouldThrowInvalidCredentialException() {
		// given
		String memberId = "memberId";
		Item item = createItemDomain(memberId);

		given(accountReader.existsByMemberId(memberId)).willReturn(false);

		// when // then
		assertThatThrownBy(() -> registerItemProcessor.registerItem(item))
			.isInstanceOf(InvalidCredentialsException.class);
	}

	private Item createItemDomain(String memberId) {
		return Item.builder()
			.name("itemA")
			.cost(new Cost(10000, 100))
			.stock(10)
			.introduction("hello")
			.itemType(ItemType.NORMAL_ORDER)
			.preOrderSchedule(null)
			.seller(Seller.builder().memberId(memberId).build())
			.build();
	}
}
