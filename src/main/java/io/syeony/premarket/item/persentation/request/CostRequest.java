package io.syeony.premarket.item.persentation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CostRequest(
	@Min(value = 1, message = "The price field can't be less than 1")
	@NotNull(message = "The price field is required")
	Integer price,

	@Min(value = 0, message = "The discount field can't be less than 0")
	@NotNull(message = "The discount field is required")
	Integer discount
) {
}
