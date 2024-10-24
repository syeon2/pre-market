package io.syeony.premarket.order.infrastructure;

import org.springframework.stereotype.Component;

import io.syeony.premarket.order.domain.model.OrderDetail;
import io.syeony.premarket.order.infrastructure.persistence.entity.OrderDetailEntity;

@Component
public final class OrderDetailMapper {

	public OrderDetailEntity toEntity(OrderDetail orderDetail, String orderId) {
		return OrderDetailEntity.builder()
			.totalPrice(orderDetail.getTotalPrice())
			.totalDiscount(orderDetail.getTotalDiscount())
			.quantity(orderDetail.getQuantity())
			.itemId(orderDetail.getItemNo())
			.orderId(orderId)
			.build();
	}
}
