package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements ItemRepository {

	private final JpaItemRepository itemRepository;
	private final ItemMapper itemMapper;

	@Override
	public Long register(Item item) {
		ItemEntity savedItem = itemRepository.save(itemMapper.toEntity(item));
		return savedItem.getId();
	}

	@Override
	public void deactivate(Item item) {
		
	}
}
