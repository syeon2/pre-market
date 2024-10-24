package io.syeony.premarket.order.presentation.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.order.domain.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
	@JsonProperty(value = "order_details")
	@NotNull(message = "The order details field is required")
	List<OrderDetailRequest> orderDetails,

	@JsonProperty(value = "shipping_address")
	@NotNull(message = "The shipping address field is required")
	ShippingAddressRequest shippingAddress,

	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id field is required")
	String memberId
) {
	public Order toDomain() {
		return Order.builder()
			.normalOrderDetails(orderDetails.stream()
				.map(OrderDetailRequest::toDomain)
				.toList())
			.shippingAddress(shippingAddress.toString())
			.customerId(memberId)
			.build();
	}
}
