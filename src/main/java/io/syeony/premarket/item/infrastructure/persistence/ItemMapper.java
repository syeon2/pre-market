package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;

@Component
public class ItemMapper {

	public ItemEntity toEntity(Item item) {
		return ItemEntity.builder()
			.itemId(item.getItemId())
			.name(item.getName())
			.price(item.getCost().getPrice())
			.discount(item.getCost().getDiscount())
			.stock(item.getStock())
			.introduction(item.getIntroduction())
			.type(item.getItemType())
			.preOrderSchedule(item.getPreOrderSchedule())
			.memberId(item.getMemberId().value())
			.status(item.getStatus())
			.categoryId(1L)
			.build();
	}
}
