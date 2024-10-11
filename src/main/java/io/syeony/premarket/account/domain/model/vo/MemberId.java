package io.syeony.premarket.account.domain.model.vo;

public record MemberId(
	String value
) {
	public static MemberId of(String value) {
		return new MemberId(value);
	}
}
