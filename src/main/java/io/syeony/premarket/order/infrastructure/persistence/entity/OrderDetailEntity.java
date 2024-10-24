package io.syeony.premarket.order.infrastructure.persistence.entity;

import io.syeony.premarket.support.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "order_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "total_price", columnDefinition = "int", nullable = false)
	private Integer totalPrice;

	@Column(name = "total_discount", columnDefinition = "int", nullable = false)
	private Integer totalDiscount;

	@Column(name = "quantity", columnDefinition = "int", nullable = false)
	private Integer quantity;

	@Column(name = "item_id", columnDefinition = "bigint", nullable = false)
	private Long itemId;

	@Column(name = "order_id", columnDefinition = "varchar(60)", nullable = false)
	private String orderId;

}

