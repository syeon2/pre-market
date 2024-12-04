package io.syeony.premarket.member.domain.processor;

import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.processor.reader.RefreshTokenReader;
import io.syeony.premarket.member.domain.processor.util.AuthorizationTokenProvider;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import io.syeony.premarket.support.error.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefreshTokenVerifier {

	private final RefreshTokenReader refreshTokenReader;
	private final AuthorizationTokenProvider authorizationTokenProvider;

	public AuthorizationToken verify(final String email, final String refreshToken) {
		refreshTokenReader.findByEmail(email).ifPresentOrElse(token -> {
			if (!token.isValid(refreshToken)) {
				throw new InvalidTokenException("Invalid refresh token: " + refreshToken);
			}
		}, () -> {
			throw new InvalidCredentialsException("Invalid email: " + email);
		});

		return authorizationTokenProvider.generateAuthorizeToken(email);
	}
}
