package io.syeony.premarket.account.domain.model;

public record AuthorizationToken(
	String accessToken,
	String refreshToken
) {

}
