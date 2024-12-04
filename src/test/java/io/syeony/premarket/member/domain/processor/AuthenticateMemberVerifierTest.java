package io.syeony.premarket.member.domain.processor;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.processor.reader.AuthenticateMember;
import io.syeony.premarket.member.domain.processor.util.AuthorizationTokenProvider;
import io.syeony.premarket.member.infrastructure.security.AuthenticationMemberManager;
import io.syeony.premarket.member.infrastructure.security.JwtTokenProvider;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;

class AuthenticateMemberVerifierTest extends UnitTestSupport {

	@InjectMocks
	private AuthenticateMemberVerifier processor;

	@Mock
	private AuthenticateMember authenticateMember = mock(AuthenticationMemberManager.class);

	@Mock
	private AuthorizationTokenProvider authorizationTokenProvider = mock(JwtTokenProvider.class);

	@BeforeEach
	void setUp() {
		processor = new AuthenticateMemberVerifier(authenticateMember, authorizationTokenProvider);
	}

	@Test
	@DisplayName(value = "Authenticate member and return authorization token generated this method")
	void verify() {
		// given
		String email = "waterkite94@gmail.com";
		String password = "rawPassword";

		given(authenticateMember.authenticate(email, password)).willReturn(true);
		given(authorizationTokenProvider.generateAuthorizeToken(email))
			.willReturn(new AuthorizationToken("accessToken", "refreshToken"));

		// when
		AuthorizationToken authenticate = processor.verify(email, password);

		// then
		Mockito.verify(authenticateMember, times(1)).authenticate(email, password);
		Mockito.verify(authorizationTokenProvider, times(1)).generateAuthorizeToken(email);

		assertThat(authenticate.accessToken()).isEqualTo("accessToken");
		assertThat(authenticate.refreshToken()).isEqualTo("refreshToken");
	}

	@Test
	@DisplayName(value = "Should throw error when the method is provided with wrong email or password")
	void verify_whenWrongEmailOrPassword() {
		// given
		String email = "waterkite94@gmail.com";
		String password = "rawPassword";

		given(authenticateMember.authenticate(email, password)).willReturn(false);

		// when // then
		assertThatThrownBy(() -> processor.verify(email, password))
			.isInstanceOf(InvalidCredentialsException.class);
	}
}
