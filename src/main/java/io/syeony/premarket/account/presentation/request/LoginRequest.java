package io.syeony.premarket.account.presentation.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@Email(message = "The email field must contains a valid email address")
	@NotBlank(message = "The email field is required")
	String email,
	
	@NotBlank(message = "The password field is required")
	@Length(min = 8, max = 20, message = "The password must be between 8 and 20 characters")
	String password
) {
}
