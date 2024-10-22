package io.syeony.premarket.item.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.item.application.dto.EditItemDto;
import io.syeony.premarket.item.application.dto.RegisterItemDto;
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
	public Long registerItem(RegisterItemDto itemDto) {
		return registerItemProcessor.registerItem(itemDto.toDomain());
	}

	@Transactional
	public void deactivateItem(String memberId, String itemId) {
		deactivateItemProcessor.deactivateItem(memberId, itemId);
	}

	@Transactional
	public void editItem(String itemId, String memberId, EditItemDto itemDto) {
		editItemProcessor.edit(itemId, memberId, itemDto.toDomain());
	}
}
