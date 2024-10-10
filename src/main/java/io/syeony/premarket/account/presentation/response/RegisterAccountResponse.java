package io.syeony.premarket.account.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterAccountResponse(
	@JsonProperty(value = "member_id")
	String memberId
) {
}
