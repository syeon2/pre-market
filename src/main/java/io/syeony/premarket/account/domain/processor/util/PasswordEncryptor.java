package io.syeony.premarket.account.domain.processor.util;

public interface PasswordEncryptor {

	String encrypt(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);
}
