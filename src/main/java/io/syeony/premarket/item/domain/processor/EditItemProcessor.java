package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditItemProcessor {

	private final ItemWriter itemWriter;
	private final ItemReader itemReader;

	public void edit(String itemId, Item item) {
		Item findItem = itemReader.findByItemId(itemId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid item id"));

		if (!findItem.verifyMemberId(item.getSeller().getMemberId())) {
			throw new InvalidCredentialsException("Invalid member id");
		}

		itemWriter.changeItemInfo(itemId, item);
	}
}
