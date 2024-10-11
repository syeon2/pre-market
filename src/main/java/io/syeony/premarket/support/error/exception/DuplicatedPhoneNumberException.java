package io.syeony.premarket.support.error.exception;

import org.springframework.http.HttpStatus;

import io.syeony.premarket.support.error.SystemException;

public class DuplicatedPhoneNumberException extends SystemException {
	public DuplicatedPhoneNumberException(String value) {
		super("Duplicated phone number", HttpStatus.CONFLICT, value);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.CONFLICT;
	}
}
