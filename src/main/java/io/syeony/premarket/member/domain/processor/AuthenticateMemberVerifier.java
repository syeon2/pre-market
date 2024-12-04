package io.syeony.premarket.member.domain.processor;

import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.processor.reader.AuthenticateMember;
import io.syeony.premarket.member.domain.processor.util.AuthorizationTokenProvider;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticateMemberVerifier {

	private final AuthenticateMember authenticateMember;
	private final AuthorizationTokenProvider authorizationTokenProvider;

	public AuthorizationToken verify(final String email, final String password) {
		if (!authenticateMember.authenticate(email, password)) {
			throw new InvalidCredentialsException("Invalid email or password");
		}

		return authorizationTokenProvider.generateAuthorizeToken(email);
	}
}
