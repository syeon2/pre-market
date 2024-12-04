package io.syeony.premarket.member.domain.model.vo;

public record Password(
	String rawPassword,
	String encryptPassword
) {
	public static Password ofRawPassword(final String rawPassword) {
		return new Password(rawPassword, null);
	}

	public static Password from(final String rawPassword, final String encryptPassword) {
		return new Password(rawPassword, encryptPassword);
	}
}
