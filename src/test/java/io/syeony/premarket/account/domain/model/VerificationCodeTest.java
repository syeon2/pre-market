package io.syeony.premarket.account.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VerificationCodeTest {

	@Test
	@DisplayName("isValid should return true when the provided value matches the VerificationCode")
	void isValid_shouldReturnTrue_whenValueMatches() {
		// given
		String codeValue = "123456";
		VerificationCode verificationCode = new VerificationCode(codeValue);

		// when
		boolean result = verificationCode.isValid("123456");

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("isValid should return false when the provided value is null")
	void isValid_shouldReturnFalse_whenValueIsNull() {
		// given
		String codeValue = "123456";
		VerificationCode verificationCode = new VerificationCode(codeValue);

		// when
		boolean result = verificationCode.isValid(null);

		// then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("isValid should return false when the provided value does not match the VerificationCode")
	void isValid_shouldReturnFalse_whenValueDoesNotMatch() {
		// given
		String codeValue = "123456";
		VerificationCode verificationCode = new VerificationCode(codeValue);

		// when
		boolean result = verificationCode.isValid("654321");

		// then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("isValid should return false when the VerificationCode value is null")
	void isValid_shouldReturnFalse_whenVerificationCodeValueIsNull() {
		// given
		VerificationCode verificationCode = new VerificationCode(null);

		// when
		boolean result = verificationCode.isValid("123456");

		// then
		assertThat(result).isFalse();
	}
}
