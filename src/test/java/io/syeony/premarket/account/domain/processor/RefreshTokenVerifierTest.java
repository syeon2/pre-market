package io.syeony.premarket.account.domain.processor;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.account.domain.model.AuthorizationToken;
import io.syeony.premarket.account.domain.model.RefreshToken;
import io.syeony.premarket.account.domain.processor.reader.RefreshTokenReader;
import io.syeony.premarket.account.domain.processor.util.AuthorizationTokenProvider;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import io.syeony.premarket.support.error.exception.InvalidTokenException;

class RefreshTokenVerifierTest extends UnitTestSupport {

	@InjectMocks
	private RefreshTokenVerifier verifier;

	@Mock
	private RefreshTokenReader refreshTokenReader = mock(RefreshTokenReader.class);

	@Mock
	private AuthorizationTokenProvider authorizationTokenProvider = mock(AuthorizationTokenProvider.class);

	@BeforeEach
	void setUp() {
		verifier = new RefreshTokenVerifier(refreshTokenReader, authorizationTokenProvider);
	}

	@Test
	@DisplayName(value = "Verify refresh token successfully and return renew access and refresh tokens")
	void verify_shouldReturnRenewTokens() {
		// given
		String email = "waterkite94@gmail.com";
		String refreshToken = "123456";

		given(refreshTokenReader.findByEmail(email))
			.willReturn(Optional.of(new RefreshToken(refreshToken)));
		given(authorizationTokenProvider.generateAuthorizeToken(email))
			.willReturn(new AuthorizationToken("access_token_value", "refresh_token_value"));

		// when
		AuthorizationToken renewToken = verifier.verify(email, refreshToken);

		// then
		verify(refreshTokenReader, times(1)).findByEmail(email);
		assertThat(renewToken.accessToken()).isEqualTo("access_token_value");
		assertThat(renewToken.refreshToken()).isEqualTo("refresh_token_value");
	}

	@Test
	@DisplayName(value = "Should throw InvalidCredentialsException when the method is provided with wrong email")
	void verify_shouldThrowInvalidCredentialsException() {
		// given
		String email = "waterkite94@gmail.com";
		String refreshToken = "123456";

		given(refreshTokenReader.findByEmail(email)).willReturn(Optional.empty());

		// when // then
		assertThatThrownBy(() -> verifier.verify(email, refreshToken))
			.isInstanceOf(InvalidCredentialsException.class);
	}

	@Test
	@DisplayName(value = "Should throw InvalidTokenException when the method is provided with wrong token")
	void verify_shouldThrowInvalidTokenException() {
		// given
		String email = "waterkite94@gmail.com";
		String refreshToken = "123456";

		given(refreshTokenReader.findByEmail(email))
			.willReturn(Optional.of(new RefreshToken("different refresh token")));

		// when // then
		assertThatThrownBy(() -> verifier.verify(email, refreshToken))
			.isInstanceOf(InvalidTokenException.class);

	}
}
