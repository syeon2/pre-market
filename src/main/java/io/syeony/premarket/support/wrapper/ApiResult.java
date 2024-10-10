package io.syeony.premarket.support.wrapper;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiResult<T> {

	private final Integer code;
	private final String message;
	private final T data;

	private ApiResult(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResult<T> of(HttpStatus status, String message, T data) {
		return new ApiResult<>(status.value(), message, data);
	}

	public static <T> ApiResult<T> ok(T data) {
		return ApiResult.of(HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), data);
	}

	public static <T> ApiResult<T> created(T data) {
		return ApiResult.of(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), data);
	}

	public static <T> ApiResult<T> error(HttpStatus status, String message) {
		return ApiResult.of(status, message, null);
	}
}
