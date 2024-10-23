package io.syeony.premarket.item.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final ItemReader itemReader;

	@Transactional
	public Long registerItem(final Item itemDomain) {
		return registerItemProcessor.registerItem(itemDomain);
	}

	@Transactional
	public void deactivateItem(String memberId, String itemId) {
		deactivateItemProcessor.deactivateItem(memberId, itemId);
	}

	@Transactional
	public void editItem(String itemId, Item itemDomain) {
		editItemProcessor.edit(itemId, itemDomain);
	}

	@Transactional(readOnly = true)
	public Page<Item> retrieveRegisteredItems(String memberId, Pageable pageable) {
		return itemReader.findItemsByMemberId(memberId, pageable);
	}

	@Transactional(readOnly = true)
	public Page<Item> retrieveCategoryItems(Long categoryId, Pageable pageable) {
		return itemReader.findItemsByCategoryId(categoryId, pageable);
	}

	@Transactional(readOnly = true)
	public Item retrieveItemDetail(Long itemId) {
		return itemReader.retrieveItemDetailByItemId(itemId);
	}
}
