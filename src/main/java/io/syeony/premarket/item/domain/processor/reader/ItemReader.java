package io.syeony.premarket.item.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.item.domain.model.Item;

public interface ItemReader {

	Optional<Item> findByItemId(String itemId);
}
