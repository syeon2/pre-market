package io.syeony.premarket.item.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements ItemWriter, ItemReader {

	private final ItemRepository itemRepository;
	private final ItemMapper itemMapper;

	@Override
	public Long register(final Item item) {
		ItemEntity savedItem = itemRepository.save(itemMapper.toEntity(item));
		return savedItem.getId();
	}

	@Override
	public void deactivate(Item item) {
		itemRepository.findByItemId(item.getItemId())
			.ifPresent(entity -> entity.changeStatusToDelete(item.getStatus()));
	}

	@Override
	public void changeItemInfo(String itemId, Item item) {
		itemRepository.findByItemId(itemId)
			.ifPresent(entity -> entity.changeItemInfo(
				item.getItemName(), item.getCost().getPrice(),
				item.getCost().getDiscount(), item.getStock(),
				item.getIntroduction(), item.getCategory().getNo()
			));
	}

	@Override
	public Optional<Item> findByItemId(String itemId) {
		return itemRepository.findByItemId(itemId)
			.map(itemMapper::toDomain);
	}

	@Override
	public Page<Item> findItemsByMemberId(String memberId, Pageable pageable) {
		return itemRepository.findItemsByMemberId(memberId, pageable);
	}

	@Override
	public Page<Item> findItemsByCategoryNo(Long categoryId, Pageable pageable) {
		return itemRepository.findItemsByCategoryId(categoryId, pageable);
	}

	@Override
	public Item retrieveItemDetailByItemNo(Long itemNo) {
		return itemRepository.findItemDetailByItemId(itemNo);
	}

	@Override
	public List<Item> findItemsByNos(List<Long> itemNos) {
		return itemRepository.findAllById(itemNos).stream()
			.map(itemMapper::toDomain)
			.toList();
	}

	@Override
	public Optional<Item> findItemByNo(Long itemNo) {
		return itemRepository.findById(itemNo)
			.map(itemMapper::toDomain);
	}

}
