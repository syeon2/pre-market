package io.syeony.premarket.account.infrastructure.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.syeony.premarket.account.domain.processor.reader.AuthenticateAccount;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationAccountManager implements AuthenticateAccount {

	private final AuthenticationManager authenticationManager;

	@Override
	public boolean authenticate(String email, String password) {
		Authentication authenticate = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(email, password));

		return authenticate.isAuthenticated();
	}
}
