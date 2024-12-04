package io.syeony.premarket.member.domain.processor.util;

public interface PasswordEncryptor {

	String encrypt(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
}
