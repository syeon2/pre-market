package io.syeony.premarket.item.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

	private Long itemNo;
	private String itemId;
	private String itemName;
	private Cost cost;
	private Integer stock;
	private String introduction;
	private ItemType itemType;
	private LocalDateTime preOrderSchedule;
	private Seller seller;
	private Category category;
	private AuditTimestamps auditTimestamps;
	private EntityStatus status;

	public Item initializeForRegister() {
		return Item.builder()
			.itemId(generateItemId())
			.itemName(itemName)
			.cost(cost)
			.stock(stock)
			.introduction(introduction)
			.itemType(itemType)
			.preOrderSchedule(preOrderSchedule)
			.seller(seller)
			.category(category)
			.status(EntityStatus.ALIVE)
			.build();
	}

	private String generateItemId() {
		return UUID.randomUUID().toString();
	}

	public boolean verifyMemberId(String memberId) {
		return seller.getMemberId().equals(memberId);
	}

	public Item deactivateStatus() {
		return Item.builder()
			.itemId(itemId)
			.status(EntityStatus.DELETED)
			.build();
	}

	public boolean isPreOrderType() {
		return itemType == ItemType.PRE_ORDER;
	}

	public boolean isNormalOrderType() {
		return itemType == ItemType.NORMAL_ORDER;
	}

	public boolean validateItem() {
		if (itemType.equals(ItemType.PRE_ORDER) && preOrderSchedule != null) {
			return true;
		}
		return itemType.equals(ItemType.NORMAL_ORDER) && preOrderSchedule == null;
	}

	public boolean isPreOrderAvailable() {
		return itemType == ItemType.PRE_ORDER
			&& preOrderSchedule != null
			&& preOrderSchedule.isAfter(LocalDateTime.now());
	}
}
