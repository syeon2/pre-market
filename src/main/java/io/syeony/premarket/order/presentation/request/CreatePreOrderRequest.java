package io.syeony.premarket.order.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.order.domain.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePreOrderRequest(
	@JsonProperty(value = "pre_order_detail")
	@NotNull(message = "The pre order detail field is required")
	PreOrderDetailRequest preOrderDetail,

	@JsonProperty(value = "shipping_address")
	@NotNull(message = "The shipping address field is required")
	ShippingAddressRequest shippingAddressRequest,

	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id field is required")
	String memberId
) {
	public Order toDomain() {
		return Order.builder()
			.preOrderDetail(preOrderDetail.toDomain())
			.shippingAddress(shippingAddressRequest.toString())
			.customerId(memberId)
			.build();
	}
}
