package io.syeony.premarket.member.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RenewTokensRequest(
	@Email(message = "The email field must contains a valid email address")
	@NotBlank(message = "The email field is required")
	String email,

	@JsonProperty(value = "refresh_token")
	@NotBlank(message = "The refresh token is required")
	String refreshToken
) {
}
