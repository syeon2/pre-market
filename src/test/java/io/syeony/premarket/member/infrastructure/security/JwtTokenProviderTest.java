package io.syeony.premarket.member.infrastructure.security;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.member.domain.model.AuthorizationToken;

class JwtTokenProviderTest extends UnitTestSupport {

	@InjectMocks
	private JwtTokenProvider jwtTokenProvider;

	@BeforeEach
	void setUp() {
		jwtTokenProvider = new JwtTokenProvider("ccd8631d022f194728174592850cc594d18ae1f6fds");
	}

	@Test
	@DisplayName(value = "Generate token and return String type token")
	void generateToken() {
		// given
		String email = "waterkite94@gmail.com";

		// when
		String token = jwtTokenProvider.generateToken(email);

		// then
		assertThat(token).isNotNull();
	}

	@Test
	@DisplayName(value = "Generate token and return Authorization Object type token")
	void generateAuthorizeToken() {
		// given
		String email = "waterkite94@gmail.com";

		// when
		AuthorizationToken authorizationToken = jwtTokenProvider.generateAuthorizeToken(email);

		// then
		assertThat(authorizationToken.accessToken()).isNotNull();
		assertThat(authorizationToken.refreshToken()).isNotNull();
	}

	@Test
	@DisplayName(value = "Should extract the correct username from the token payload")
	void extractUsername() {
		// given
		String email = "waterkite94@gmail.com";
		String token = jwtTokenProvider.generateToken(email);

		// when
		String username = jwtTokenProvider.extractUsername(token);

		// then
		assertThat(username).isEqualTo(email);
	}

	@Test
	@DisplayName(value = "Should extract the correct expiration time from the token payload")
	void extractExpirationTime() {
		// given
		String email = "waterkite94@gmail.com";
		String token = jwtTokenProvider.generateToken(email);

		// when
		Date date = jwtTokenProvider.extractExpiration(token);

		// then
		Date afterDate = new Date(System.currentTimeMillis() + JwtTokenProvider.EXPIRATION_TIME);
		assertThat(date.before(afterDate)).isTrue();
		assertThat(date.after(new Date())).isTrue();
	}
}
