package io.syeony.premarket.order.domain.model;

import java.util.List;
import java.util.UUID;

import io.syeony.premarket.support.common.AuditTimestamps;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Order {

	private Long orderNo;
	private String orderId;
	private Integer totalPrice;
	private List<OrderDetail> orderDetails;
	private String shippingAddress;
	private String customerId;
	private OrderStatus status;
	private AuditTimestamps auditTimestamps;

	public Order initializeForCreate(List<OrderDetail> orderDetails) {
		return Order.builder()
			.orderId(UUID.randomUUID().toString())
			.totalPrice(calculateTotalPrice(orderDetails))
			.orderDetails(orderDetails)
			.customerId(customerId)
			.status(OrderStatus.PAYMENT_COMPLETED)
			.shippingAddress(shippingAddress)
			.build();
	}

	private Integer calculateTotalPrice(List<OrderDetail> orderDetails) {
		return orderDetails.stream()
			.mapToInt(domain -> domain.getTotalPrice() - domain.getTotalDiscount())
			.sum();

	}
}
