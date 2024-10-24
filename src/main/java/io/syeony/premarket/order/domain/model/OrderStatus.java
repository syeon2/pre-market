package io.syeony.premarket.order.domain.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
	PAYMENT_COMPLETED("결제 완료"),
	ORDER_CANCELLED("주문 취소"),
	IN_DELIVERY("배송 중"),
	DELIVERY_COMPLETED("배송 완료"),
	PURCHASE_CONFIRMED("구매 결정"),
	RETURN_IN_PROGRESS("반품 중"),
	RETURN_COMPLETED("반품 완료");

	private final String description;

	OrderStatus(String description) {
		this.description = description;
	}
}
