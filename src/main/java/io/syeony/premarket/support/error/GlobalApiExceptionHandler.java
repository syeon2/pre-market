package io.syeony.premarket.support.error;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.syeony.premarket.support.common.ApiResult;
import io.syeony.premarket.support.error.exception.DuplicatedEmailException;
import io.syeony.premarket.support.error.exception.DuplicatedPhoneNumberException;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import io.syeony.premarket.support.error.exception.InvalidVerificationCodeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public final class GlobalApiExceptionHandler {

	@ExceptionHandler(value = InvalidCredentialsException.class)
	private ResponseEntity<ApiResult<Void>> handlerInvalidCredentialsException(InvalidCredentialsException exception) {
		log.error("InvalidCredentialsException", exception);
		return ResponseEntity
			.status(exception.getHttpStatus())
			.body(ApiResult.error(exception.getMessage()));
	}

	@ExceptionHandler(value = InvalidVerificationCodeException.class)
	private ResponseEntity<ApiResult<Void>> handleInValidVerificationCodeException(
		InvalidVerificationCodeException exception) {
		log.error("InValidVerificationCodeException", exception);
		return ResponseEntity
			.status(exception.getHttpStatus())
			.body(ApiResult.error(exception.getMessage()));
	}

	@ExceptionHandler(value = DuplicatedEmailException.class)
	private ResponseEntity<ApiResult<Void>> handleDuplicatedEmailException(DuplicatedEmailException exception) {
		log.error("DuplicatedEmailException", exception);
		return ResponseEntity
			.status(exception.getHttpStatus())
			.body(ApiResult.error(exception.getMessage()));
	}

	@ExceptionHandler(value = DuplicatedPhoneNumberException.class)
	private ResponseEntity<ApiResult<Void>> handleDuplicatedPhoneNumberException(
		DuplicatedPhoneNumberException exception) {
		log.error("DuplicatedPhoneNumberException", exception);
		return ResponseEntity
			.status(exception.getHttpStatus())
			.body(ApiResult.error(exception.getMessage()));
	}

	@ExceptionHandler(BindException.class)
	private ResponseEntity<ApiResult<?>> handleBind(BindException exception) {
		log.error("BindException", exception);
		String collect = exception.getAllErrors().stream()
			.map(ObjectError::getDefaultMessage)
			.collect(Collectors.joining(", ", "[", "]"));

		return ResponseEntity
			.badRequest()
			.body(ApiResult.error(collect));
	}

	@ExceptionHandler(RuntimeException.class)
	private ResponseEntity<ApiResult<?>> handleRuntimeException(RuntimeException exception) {
		log.error("RuntimeException", exception);
		return ResponseEntity
			.badRequest()
			.body(ApiResult.error(exception.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	private ResponseEntity<ApiResult<?>> handleException(Exception exception) {
		log.error("Exception", exception);
		return ResponseEntity
			.internalServerError()
			.body(ApiResult.error("Internal server error"));
	}
}
