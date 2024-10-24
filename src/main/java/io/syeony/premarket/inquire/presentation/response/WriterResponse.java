package io.syeony.premarket.inquire.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WriterResponse(
	@JsonProperty(value = "member_id")
	String memberNo,

	@JsonProperty(value = "member_name")
	String memberName
) {
}
