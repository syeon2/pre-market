package io.syeony.premarket.account.domain.model;

public record VerificationCode(String value) {

	public boolean isValid(String value) {
		return this.value != null && this.value.equals(value);
	}
}
