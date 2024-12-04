package io.syeony.premarket.member.domain.model.vo;

public record Password(
	String rawPassword,
	String encryptPassword
) {
}
