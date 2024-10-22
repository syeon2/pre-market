package io.syeony.premarket.item.domain.processor.repository;

import io.syeony.premarket.item.domain.model.Item;

public interface ItemRepository {

	Long register(Item item);

	void deactivate(Item item);

}
