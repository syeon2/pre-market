package io.syeony.premarket.member.infrastructure.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.syeony.premarket.member.domain.processor.reader.AuthenticateMember;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationMemberManager implements AuthenticateMember {

	private final AuthenticationManager authenticationManager;

	@Override
	public boolean authenticate(final String email, final String password) {
		Authentication authenticate = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(email, password));

		return authenticate.isAuthenticated();
	}
}
