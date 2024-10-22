package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;
import io.syeony.premarket.support.common.AuditTimestamps;

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
			.categoryId(item.getCategoryId())
			.build();
	}

	public Item toDomain(ItemEntity entity) {
		return Item.builder()
			.id(entity.getId())
			.itemId(entity.getItemId())
			.name(entity.getName())
			.cost(new Cost(entity.getPrice(), entity.getDiscount()))
			.stock(entity.getStock())
			.introduction(entity.getIntroduction())
			.itemType(entity.getType())
			.preOrderSchedule(entity.getPreOrderSchedule())
			.memberId(new MemberId(entity.getMemberId()))
			.auditTimestamps(new AuditTimestamps(entity.getCreatedAt(), entity.getUpdatedAt()))
			.status(entity.getStatus())
			.categoryId(entity.getCategoryId())
			.build();
	}
}
