package io.syeony.premarket.item.application.dto;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import lombok.Builder;

@Builder
public record EditItemDto(
	String name,
	CostDto costDto,
	Integer stock,
	String introduction,
	Long categoryId
) {
	public Item toDomain() {
		return Item.builder()
			.name(name)
			.cost(new Cost(costDto.price, costDto.discount))
			.stock(stock)
			.introduction(introduction)
			.category(Category.builder().id(categoryId).build())
			.build();
	}

	public record CostDto(
		Integer price,
		Integer discount
	) {
	}
}
