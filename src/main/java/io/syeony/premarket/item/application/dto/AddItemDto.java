package io.syeony.premarket.item.application.dto;

import io.syeony.premarket.item.domain.model.ItemType;
import lombok.Builder;

@Builder
public record AddItemDto(
	String name,
	Cost cost,
	Integer stock,
	String introduction,
	ItemType itemType,
	PreOrderSchedule preOrderSchedule
) {
	public record Cost(
		Integer price,
		Integer discount
	) {
	}

	public record PreOrderSchedule(
		Integer year,
		Integer month,
		Integer date,
		Integer hour,
		Integer minute
	) {
	}
}
