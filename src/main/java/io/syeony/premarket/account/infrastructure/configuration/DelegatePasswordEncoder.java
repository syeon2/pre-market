package io.syeony.premarket.account.infrastructure.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

import io.syeony.premarket.account.domain.processor.util.PasswordEncryptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatePasswordEncoder implements PasswordEncryptor {

	private final PasswordEncoder passwordEncoder;

	@Override
	public String encrypt(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
