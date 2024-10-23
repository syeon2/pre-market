package io.syeony.premarket.item.persentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterItemResponse(
	@JsonProperty(value = "item_no")
	Long itemNo
) {
}
