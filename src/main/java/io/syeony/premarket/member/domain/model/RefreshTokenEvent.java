package io.syeony.premarket.member.domain.model;

public record RefreshTokenEvent(
	String email,
	String refreshToken
) {
}
