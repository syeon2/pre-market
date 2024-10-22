package io.syeony.premarket.item.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.support.common.BaseEntity;
import io.syeony.premarket.support.common.EntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "item_id", columnDefinition = "varchar(60)", nullable = false)
	private String itemId;

	@Column(name = "name", columnDefinition = "varchar(60)", nullable = false)
	private String name;

	@Column(name = "price", columnDefinition = "int", nullable = false)
	private Integer price;

	@Column(name = "discount", columnDefinition = "int", nullable = false)
	private Integer discount;

	@Column(name = "stock", columnDefinition = "int", nullable = false)
	private Integer stock;

	@Column(name = "introduction", columnDefinition = "varchar(255)", nullable = false)
	private String introduction;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "varchar(60)", nullable = false)
	private ItemType type;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
	@Column(name = "pre_order_schedule", columnDefinition = "timestamp")
	private LocalDateTime preOrderSchedule;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(30)", nullable = false)
	private EntityStatus status;

	@Column(name = "category_id", columnDefinition = "bigint", nullable = false)
	private Long categoryId;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

	@Builder
	private ItemEntity(Long id, String itemId, String name, Integer price, Integer discount, Integer stock,
		String introduction, ItemType type, LocalDateTime preOrderSchedule, EntityStatus status, Long categoryId,
		String memberId) {
		this.id = id;
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.introduction = introduction;
		this.type = type;
		this.preOrderSchedule = preOrderSchedule;
		this.status = status;
		this.categoryId = categoryId;
		this.memberId = memberId;
	}

	public void changeStatusToDelete(EntityStatus status) {
		this.status = status;
	}
}
