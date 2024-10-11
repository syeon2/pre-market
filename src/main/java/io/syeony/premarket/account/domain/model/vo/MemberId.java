package io.syeony.premarket.account.domain.model.vo;

import java.util.UUID;

public record MemberId(
	String value
) {
	public static MemberId generate() {
		return new MemberId(UUID.randomUUID().toString());
	}
}
