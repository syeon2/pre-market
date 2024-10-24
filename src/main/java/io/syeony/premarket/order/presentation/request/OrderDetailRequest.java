package io.syeony.premarket.order.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.order.domain.model.OrderDetail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderDetailRequest(
	@JsonProperty(value = "item_no")
	@NotNull(message = "The item no field is required")
	Long itemNo,

	@JsonProperty(value = "quantity")
	@NotNull(message = "The quantity field is required")
	@Min(value = 1, message = "The quantity field can't be less than 1")
	Integer quantity
) {
	public OrderDetail toDomain() {
		return OrderDetail
			.builder()
			.itemNo(itemNo)
			.quantity(quantity)
			.build();
	}
}
