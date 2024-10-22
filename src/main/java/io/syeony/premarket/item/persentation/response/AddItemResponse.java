package io.syeony.premarket.item.persentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddItemResponse(
	@JsonProperty(value = "item_id")
	Integer itemId
) {
}
