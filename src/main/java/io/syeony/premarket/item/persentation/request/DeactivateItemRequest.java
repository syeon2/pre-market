package io.syeony.premarket.item.persentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record DeactivateItemRequest(
	@JsonProperty(value = "seller_id")
	@NotBlank(message = "The seller id field is required")
	String sellerId,

	@JsonProperty(value = "item_id")
	@NotBlank(message = "The item id field is required")
	String itemId
) {
}
