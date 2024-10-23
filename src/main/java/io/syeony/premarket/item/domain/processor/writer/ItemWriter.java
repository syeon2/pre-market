package io.syeony.premarket.item.domain.processor.writer;

import io.syeony.premarket.item.domain.model.Item;

public interface ItemWriter {

	Long register(Item item);

	void deactivate(Item item);

	void changeItemInfo(String itemId, Item item);
}
