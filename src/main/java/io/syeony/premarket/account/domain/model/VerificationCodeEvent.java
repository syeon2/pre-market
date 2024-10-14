package io.syeony.premarket.account.domain.model;

public record VerificationCodeEvent(
	String toEmail,
	String code
) {
	public static VerificationCodeEvent createEvent(String toEmail, String code) {
		return new VerificationCodeEvent(toEmail, code);
	}
}
