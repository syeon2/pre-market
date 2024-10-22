package io.syeony.premarket.item.application.dto;

import java.time.LocalDateTime;

import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import lombok.Builder;

@Builder
public record AddItemDto(
	String memberId,
	String name,
	CostDto costDto,
	Integer stock,
	String introduction,
	ItemType itemType,
	LocalDateTime preOrderSchedule
) {
	public Item toDomain() {
		return Item.builder()
			.name(name)
			.cost(new Cost(costDto.price, costDto.discount))
			.stock(stock)
			.introduction(introduction)
			.itemType(itemType)
			.preOrderSchedule(preOrderSchedule)
			.build();
	}

	public record CostDto(
		Integer price,
		Integer discount
	) {
	}
}
