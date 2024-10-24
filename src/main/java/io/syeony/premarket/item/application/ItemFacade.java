package io.syeony.premarket.item.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.item.domain.ItemStockHandler;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.DeactivateItemProcessor;
import io.syeony.premarket.item.domain.processor.EditItemProcessor;
import io.syeony.premarket.item.domain.processor.RegisterItemProcessor;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemFacade {

	private final RegisterItemProcessor registerItemProcessor;
	private final DeactivateItemProcessor deactivateItemProcessor;
	private final EditItemProcessor editItemProcessor;
	private final ItemStockHandler itemStockHandler;
	private final ItemReader itemReader;

	@Transactional
	public Long registerItem(final Item itemDomain) {
		return registerItemProcessor.register(itemDomain);
	}

	@Transactional
	public void deactivateItem(final String sellerId, final String itemId) {
		deactivateItemProcessor.deactivate(sellerId, itemId);
	}

	@Transactional
	public void editItem(final String itemId, final Item itemDomain) {
		editItemProcessor.edit(itemId, itemDomain);
	}

	@Transactional(readOnly = true)
	public Page<Item> findRegisteredItems(final String memberId, Pageable pageable) {
		return itemReader.findItemsByMemberId(memberId, pageable);
	}

	@Transactional(readOnly = true)
	public Page<Item> findCategoryItems(Long categoryNo, Pageable pageable) {
		return itemReader.findItemsByCategoryNo(categoryNo, pageable);
	}

	@Transactional(readOnly = true)
	public Item retrieveItemDetail(Long itemId) {
		return itemReader.retrieveItemDetailByItemId(itemId);
	}

	@Transactional
	public void deductStock(final Long itemNo, final Integer quantity) {
		itemStockHandler.deductStock(itemNo, quantity);
	}

	@Transactional
	public void increaseStock(Long itemNo, Integer quantity) {
		itemStockHandler.increaseStock(itemNo, quantity);
	}
}
