package io.syeony.premarket.member.domain.model;

public record VerificationCodeEvent(
	String toEmail,
	String code
) {
	public static VerificationCodeEvent createEvent(String toEmail, String code) {
		return new VerificationCodeEvent(toEmail, code);
	}
}
