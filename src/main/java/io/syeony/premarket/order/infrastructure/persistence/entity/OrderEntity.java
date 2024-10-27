package io.syeony.premarket.order.infrastructure.persistence.entity;

import io.syeony.premarket.order.domain.model.OrderStatus;
import io.syeony.premarket.support.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "order_id", columnDefinition = "varchar(60)", nullable = false, unique = true)
	private String orderId;

	@Column(name = "total_price", columnDefinition = "integer", nullable = false)
	private Integer totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(30)", nullable = false)
	private OrderStatus status;

	@Column(name = "shipping_address", columnDefinition = "varchar(255)", nullable = false)
	private String shippingAddress;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

}
