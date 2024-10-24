package io.syeony.premarket.order.infrastructure;

import org.springframework.stereotype.Component;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.infrastructure.persistence.entity.OrderEntity;

@Component
public final class OrderMapper {

	public OrderEntity toEntity(Order order) {
		return OrderEntity.builder()
			.orderId(order.getOrderId())
			.status(order.getStatus())
			.shippingAddress(order.getShippingAddress())
			.totalPrice(order.getTotalPrice())
			.memberId(order.getCustomerId())
			.build();
	}
}
