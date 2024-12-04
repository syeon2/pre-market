package io.syeony.premarket.member.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record IssueVerificationRequest(
	@JsonProperty(value = "to_email")
	@Email(message = "The to_email field must contains a valid email address")
	@NotBlank(message = "The to_email field is required")
	String toEmail
) {
}
