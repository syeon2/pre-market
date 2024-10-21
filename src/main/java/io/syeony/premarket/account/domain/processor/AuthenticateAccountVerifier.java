package io.syeony.premarket.account.domain.processor;

import io.syeony.premarket.account.domain.model.AuthorizationToken;
import io.syeony.premarket.account.domain.processor.reader.AuthenticateAccount;
import io.syeony.premarket.account.domain.processor.util.AuthorizationTokenProvider;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticateAccountVerifier {

	private final AuthenticateAccount authenticateAccount;
	private final AuthorizationTokenProvider authorizationTokenProvider;

	public AuthorizationToken verify(final String email, final String password) {
		if (!authenticateAccount.authenticate(email, password)) {
			throw new InvalidCredentialsException("Invalid email or password");
		}

		return authorizationTokenProvider.generateAuthorizeToken(email);
	}
}
