package io.syeony.premarket.member.domain.model;

public record AuthorizationToken(
	String accessToken,
	String refreshToken
) {

}
