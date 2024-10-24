package io.syeony.premarket.item.persentation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DeductStockRequest(
	@NotNull(message = "The quantity field is required")
	@Min(value = 1, message = "The quantity field can't be less than 1")
	Integer quantity
) {
}
