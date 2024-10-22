package io.syeony.premarket.support.error.exception;

import org.springframework.http.HttpStatus;

import io.syeony.premarket.support.error.SystemException;

public class DuplicatedEmailException extends SystemException {
	public DuplicatedEmailException(String value) {
		super("Duplicated email", HttpStatus.CONFLICT, value);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.CONFLICT;
	}
}
