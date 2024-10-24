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
	private String shippingAddress;
	private String customerId;
	private OrderStatus status;
	private AuditTimestamps auditTimestamps;

	/* Normal Order Item */
	private List<OrderDetail> normalOrderDetails;

	public Order initializeForCreate(List<OrderDetail> orderDetails) {
		return Order.builder()
			.orderId(generateUuid())
			.totalPrice(calculateTotalPrice(orderDetails))
			.normalOrderDetails(orderDetails)
			.customerId(customerId)
			.status(OrderStatus.PAYMENT_COMPLETED)
			.shippingAddress(shippingAddress)
			.build();
	}

	public List<Long> extractItemNos() {
		return normalOrderDetails.stream()
			.map(OrderDetail::getItemNo)
			.toList();
	}

	private Integer calculateTotalPrice(List<OrderDetail> orderDetails) {
		return orderDetails.stream()
			.mapToInt(domain -> domain.getTotalPrice() - domain.getTotalDiscount())
			.sum();
	}

	/* Pre Order Item */
	private OrderDetail preOrderDetail;

	public Order initializeForCreate(OrderDetail orderDetail) {
		return Order.builder()
			.orderId(generateUuid())
			.totalPrice(calculateTotalPrice(orderDetail))
			.preOrderDetail(orderDetail)
			.customerId(customerId)
			.status(OrderStatus.PAYMENT_COMPLETED)
			.shippingAddress(shippingAddress)
			.build();
	}

	private Integer calculateTotalPrice(OrderDetail orderDetail) {
		return orderDetail.getTotalPrice() - orderDetail.getTotalDiscount();
	}

	private static String generateUuid() {
		return UUID.randomUUID().toString();
	}
}
