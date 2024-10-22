package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements ItemRepository {

	private final JpaItemRepository itemRepository;

	@Override
	public Long registerItem(Item item) {
		return null;
	}
}
