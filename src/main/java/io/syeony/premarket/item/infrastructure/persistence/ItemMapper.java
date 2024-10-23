package io.syeony.premarket.item.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.infrastructure.persistence.entity.ItemEntity;
import io.syeony.premarket.support.common.AuditTimestamps;

@Component
public class ItemMapper {

	public ItemEntity toEntity(Item item) {
		return ItemEntity.builder()
			.itemId(item.getItemId())
			.name(item.getItemName())
			.price(item.getCost().getPrice())
			.discount(item.getCost().getDiscount())
			.stock(item.getStock())
			.introduction(item.getIntroduction())
			.type(item.getItemType())
			.preOrderSchedule(item.getPreOrderSchedule())
			.memberId(item.getSeller().getMemberId())
			.status(item.getStatus())
			.categoryId(item.getCategory().getNo())
			.build();
	}

	public Item toDomain(ItemEntity entity) {
		return Item.builder()
			.itemNo(entity.getId())
			.itemId(entity.getItemId())
			.itemName(entity.getName())
			.cost(new Cost(entity.getPrice(), entity.getDiscount()))
			.stock(entity.getStock())
			.introduction(entity.getIntroduction())
			.itemType(entity.getType())
			.preOrderSchedule(entity.getPreOrderSchedule())
			.seller(Seller.builder().memberId(entity.getMemberId()).build())
			.auditTimestamps(new AuditTimestamps(entity.getCreatedAt(), entity.getUpdatedAt()))
			.status(entity.getStatus())
			.category(Category.initNo(entity.getCategoryId()))
			.build();
	}
}
