package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeactivateItemProcessor {

	private final ItemReader itemReader;
	private final ItemWriter itemWriter;

	public void deactivate(String sellerId, String itemId) {
		Item item = itemReader.findByItemId(itemId).orElseThrow(() ->
			new IllegalArgumentException("Invalid item id: " + itemId));
		verifyItem(item, sellerId);
		itemWriter.deactivate(item.deactivateStatus());
	}

	private void verifyItem(Item item, String sellerId) {
		if (!item.verifyMemberId(sellerId)) {
			throw new InvalidCredentialsException("Invalid member id: " + sellerId);
		}
	}
}
