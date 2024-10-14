package io.syeony.premarket.support.error.exception;

import org.springframework.http.HttpStatus;

import io.syeony.premarket.support.error.SystemException;

public class InValidVerificationCodeException extends SystemException {
	public InValidVerificationCodeException(String value) {
		super("Invalid verification code: " + value, HttpStatus.BAD_REQUEST, value);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
