package io.syeony.premarket.account.domain.model.vo;

public record Password(
	String rawPassword,
	String encryptPassword
) {
}
