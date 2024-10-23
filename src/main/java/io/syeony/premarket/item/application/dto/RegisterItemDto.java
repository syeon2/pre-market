package io.syeony.premarket.item.application.dto;

import java.time.LocalDateTime;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.item.domain.model.Seller;
import lombok.Builder;

@Builder
public record RegisterItemDto(
	String memberId,
	String name,
	CostDto costDto,
	Integer stock,
	String introduction,
	ItemType itemType,
	LocalDateTime preOrderSchedule,
	Long categoryId
) {
	public Item toDomain() {
		return Item.builder()
			.name(name)
			.cost(new Cost(costDto.price, costDto.discount))
			.stock(stock)
			.introduction(introduction)
			.itemType(itemType)
			.preOrderSchedule(preOrderSchedule)
			.seller(Seller.builder().memberId(memberId).build())
			.category(Category.builder().no(categoryId).build())
			.build();
	}

	public record CostDto(
		Integer price,
		Integer discount
	) {
	}
}
