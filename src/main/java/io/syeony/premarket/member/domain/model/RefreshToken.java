package io.syeony.premarket.member.domain.model;

import lombok.Getter;

@Getter
public class RefreshToken {

	private final String refreshToken;

	public RefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public boolean isValid(final String token) {
		return refreshToken != null && refreshToken.equals(token);
	}
}
