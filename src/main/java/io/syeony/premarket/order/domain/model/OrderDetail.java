package io.syeony.premarket.order.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderDetail {

	private Long orderDetailNo;
	private Integer quantity;
	private Integer totalPrice;
	private Integer totalDiscount;
	private Long itemNo;
	private String orderId;

	public static OrderDetail of(Long orderDetailNo, Integer quantity, Integer totalPrice, Integer totalDiscount,
		Long itemNo, String orderId) {
		return new OrderDetail(orderDetailNo, quantity, totalPrice, totalDiscount, itemNo, orderId);
	}

	public OrderDetail initializeForCreate(Integer price, Integer discount) {
		return new OrderDetail(orderDetailNo, quantity, calculateTotalPrice(price), calculateTotalDiscount(discount),
			itemNo, orderId);
	}

	public Integer calculateTotalPrice(Integer price) {
		return quantity * price;
	}

	public Integer calculateTotalDiscount(Integer discount) {
		return quantity * discount;
	}
}
