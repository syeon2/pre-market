package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeactivateItemProcessor {

	private final ItemReader itemReader;
	private final ItemRepository itemRepository;

	public void deactivateItem(String memberId, String itemId) {
		Item item = itemReader.findByItemId(itemId).orElseThrow(() ->
			new IllegalArgumentException("Invalid item id: " + itemId));
		validateItem(item, memberId);
		itemRepository.deactivate(item.deactivateStatus());
	}

	private void validateItem(Item item, String memberId) {
		if (!item.verifyMemberId(memberId)) {
			throw new InvalidCredentialsException("Invalid member id: " + memberId);
		}
	}
}
