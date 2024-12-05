package io.syeony.premarket.member.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
	@JsonProperty(value = "access_token")
	String accessToken,
	@JsonProperty(value = "refresh_token")
	String refreshToken
) {
}
