package io.syeony.premarket.item.persentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record DeactivateItemRequest(
	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id field is required")
	String memberId,

	@JsonProperty(value = "item_id")
	@NotBlank(message = "The item id field is required")
	String itemId
) {
}