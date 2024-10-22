package io.syeony.premarket.account.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorizeTokenResponse(
	@JsonProperty(value = "access_token")
	String accessToken,
	@JsonProperty(value = "refresh_token")
	String refreshToken
) {
}
