package io.syeony.premarket.support.error.exception;

import org.springframework.http.HttpStatus;

import io.syeony.premarket.support.error.SystemException;

public class InvalidTokenException extends SystemException {
	public InvalidTokenException(String value) {
		super("Invalid token", HttpStatus.UNAUTHORIZED.value(), value);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNAUTHORIZED;
	}
}
