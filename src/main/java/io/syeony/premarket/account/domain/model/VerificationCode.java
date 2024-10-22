package io.syeony.premarket.account.domain.model;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class VerificationCode {

	private final String toEmail;
	private final String code;

	private static final Integer CODE_LENGTH = 6;
	private static final SecureRandom SECURE = new SecureRandom();

	@Builder
	private VerificationCode(String toEmail, String code) {
		this.toEmail = toEmail;
		this.code = code;
	}

	public static VerificationCode of(final String toEmail, final String code) {
		return new VerificationCode(toEmail, code);
	}

	public static VerificationCode issueVerificationCode(final String toEmail) {
		return VerificationCode.of(toEmail, generateCode());
	}

	public boolean isValid(final String code) {
		return this.code != null && this.code.equals(code);
	}

	private static String generateCode() {
		return IntStream.range(0, CODE_LENGTH)
			.mapToObj(idx -> String.valueOf(SECURE.nextInt(10)))
			.collect(Collectors.joining());
	}
}
