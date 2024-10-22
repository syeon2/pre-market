package io.syeony.premarket.support.error.exception;

import org.springframework.http.HttpStatus;

import io.syeony.premarket.support.error.SystemException;

public class VerificationCodeNotFoundException extends SystemException {
	public VerificationCodeNotFoundException(String value) {
		super("Verification Code Not Found", HttpStatus.NOT_FOUND, value);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
