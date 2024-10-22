package io.syeony.premarket.item.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {

	private Long id;
	private String itemId;
	private String name;
	private Cost cost;
	private Integer stock;
	private String introduction;
	private ItemType itemType;
	private LocalDateTime preOrderSchedule;
	private MemberId memberId;
	private AuditTimestamps auditTimestamps;
	private EntityStatus status;

	@Builder
	private Item(Long id, String itemId, String name, Cost cost, Integer stock, String introduction, ItemType itemType,
		LocalDateTime preOrderSchedule, MemberId memberId, AuditTimestamps auditTimestamps, EntityStatus status) {
		this.id = id;
		this.itemId = itemId;
		this.name = name;
		this.cost = cost;
		this.stock = stock;
		this.introduction = introduction;
		this.itemType = itemType;
		this.preOrderSchedule = preOrderSchedule;
		this.memberId = memberId;
		this.auditTimestamps = auditTimestamps;
		this.status = status;
	}

	public Item initializeForRegister() {
		return Item.builder()
			.itemId(generateItemId())
			.name(name)
			.cost(cost)
			.stock(stock)
			.introduction(introduction)
			.itemType(itemType)
			.preOrderSchedule(preOrderSchedule)
			.memberId(memberId)
			.status(EntityStatus.ALIVE)
			.build();
	}

	private String generateItemId() {
		return UUID.randomUUID().toString();
	}

	public boolean verifyMemberId(String memberId) {
		return this.memberId.value().equals(memberId);
	}

	public Item deactivateStatus() {
		return Item.builder()
			.id(id)
			.itemId(itemId)
			.name(name)
			.cost(cost)
			.stock(stock)
			.introduction(introduction)
			.itemType(itemType)
			.preOrderSchedule(preOrderSchedule)
			.memberId(memberId)
			.auditTimestamps(auditTimestamps)
			.status(EntityStatus.DELETED)
			.build();
	}
}
