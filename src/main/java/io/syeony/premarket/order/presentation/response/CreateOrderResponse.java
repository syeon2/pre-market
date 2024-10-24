package io.syeony.premarket.order.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrderResponse(
	@JsonProperty(value = "order_id")
	String orderId
) {
}
