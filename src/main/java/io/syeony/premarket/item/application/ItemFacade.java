package io.syeony.premarket.item.application;

import org.springframework.stereotype.Service;

import io.syeony.premarket.item.application.dto.AddItemDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemFacade {

	public Integer addItem(AddItemDto itemDto) {
		return 1;
	}
}
