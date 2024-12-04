package io.syeony.premarket.member.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterMemberResponse(
	@JsonProperty(value = "member_id")
	String memberId
) {
}
