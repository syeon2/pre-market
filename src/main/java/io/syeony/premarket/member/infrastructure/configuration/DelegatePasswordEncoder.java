package io.syeony.premarket.member.infrastructure.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

import io.syeony.premarket.member.domain.processor.util.PasswordEncryptor;
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
