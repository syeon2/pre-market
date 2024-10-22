package io.syeony.premarket.support.error;

import org.springframework.http.HttpStatus;

public abstract class SystemException extends RuntimeException {

	public SystemException(String format, Object... args) {
		super(String.format(format, args));
	}

	public abstract HttpStatus getHttpStatus();
}
