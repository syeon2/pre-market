package io.syeony.premarket.item.application.dto;

import java.time.LocalDateTime;

import io.syeony.premarket.item.domain.model.ItemType;

public record RetrieveRegisteredItemDto(
	Long itemId,
	String itemName,
	Integer price,
	Integer discount,
	ItemType itemType,
	LocalDateTime preOrderSchedule,
	Integer stock,
	LocalDateTime createdAt,
	Long categoryId,
	String categoryName
) {

}
