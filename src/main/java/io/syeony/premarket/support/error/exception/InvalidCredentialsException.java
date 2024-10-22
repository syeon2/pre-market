package io.syeony.premarket.support.error.exception;

import org.springframework.http.HttpStatus;

import io.syeony.premarket.support.error.SystemException;

public class InvalidCredentialsException extends SystemException {
	public InvalidCredentialsException(String value) {
		super("Invalid credentials", HttpStatus.UNAUTHORIZED, value);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNAUTHORIZED;
	}
}
