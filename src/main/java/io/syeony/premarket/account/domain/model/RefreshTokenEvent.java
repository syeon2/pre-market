package io.syeony.premarket.account.domain.model;

public record RefreshTokenEvent(
	String email,
	String refreshToken
) {
}
