package io.syeony.premarket.inquire.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeleteInquireRequest(
	@JsonProperty(value = "member_id")
	String memberId,

	@JsonProperty(value = "item_no")
	Long itemNo
) {
}
