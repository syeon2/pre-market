package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditItemProcessor {

	private final ItemRepository itemRepository;
	private final ItemReader itemReader;

	public void edit(String itemId, String memberId, Item item) {
		Item findItem = itemReader.findByItemId(itemId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid item id"));

		if (!findItem.verifyMemberId(memberId)) {
			throw new InvalidCredentialsException("Invalid member id");
		}

		itemRepository.changeItemInfo(itemId, item);
	}
}
