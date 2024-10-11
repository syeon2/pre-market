package io.syeony.premarket.account.domain.model.vo;

public record Password(
	String rawPassword,
	String encryptPassword
) {
	public static Password of(String rawPassword) {
		return new Password(rawPassword, null);
	}
}
