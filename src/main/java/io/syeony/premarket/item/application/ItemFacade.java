package io.syeony.premarket.item.application;

import org.springframework.stereotype.Service;

import io.syeony.premarket.item.application.dto.AddItemDto;
import io.syeony.premarket.item.domain.processor.DeactivateItemProcessor;
import io.syeony.premarket.item.domain.processor.RegisterItemProcessor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemFacade {

	private final RegisterItemProcessor registerItemProcessor;
	private final DeactivateItemProcessor deactivateItemProcessor;

	public Long registerItem(AddItemDto itemDto) {
		return registerItemProcessor.registerItem(itemDto.toDomain());
	}

	public void deactivateItem(String memberId, String itemId) {

	}
}
