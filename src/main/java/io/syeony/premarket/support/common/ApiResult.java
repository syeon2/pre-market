package io.syeony.premarket.support.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiResult<T> {

	private final String status;
	private final String message;
	private final T data;

	private ApiResult(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResult<T> of(String status, String message, T data) {
		return new ApiResult<>(status, message, data);
	}

	public static <T> ApiResult<T> success(T data) {
		return ApiResult.of("success", null, data);
	}

	public static <T> ApiResult<T> success() {
		return ApiResult.of("success", null, null);
	}

	public static <T> ApiResult<T> fail(String message) {
		return ApiResult.of("fail", message, null);
	}

	public static <T> ApiResult<T> error(String message) {
		return new ApiResult<>("error", message, null);
	}
}
