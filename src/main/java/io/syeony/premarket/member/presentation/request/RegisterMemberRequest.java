package io.syeony.premarket.member.presentation.request;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.Password;
import io.syeony.premarket.member.presentation.request.vo.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterMemberRequest(
	@Email(message = "The email field must contains a valid email address")
	@NotBlank(message = "The email field is required")
	String email,

	@NotBlank(message = "The password field is required")
	@Length(min = 8, max = 20, message = "The password must be between 8 and 20 characters")
	String password,

	@NotBlank(message = "The name field is required")
	String name,

	@JsonProperty(value = "phone_number")
	@NotBlank(message = "The phone number field is required")
	@Pattern(regexp = "[0-9]{10,11}", message = "The phone number must contains between 10 and 11 digits")
	String phoneNumber,

	@Valid
	@JsonProperty(value = "address")
	@NotNull(message = "The address field is required")
	AddressRequest addressRequest,

	@JsonProperty(value = "verification_code")
	@NotBlank(message = "The verification code is required")
	String verificationCode
) {
	public Member toDomain() {
		return Member.builder()
			.email(email)
			.password(Password.ofRawPassword(password))
			.name(name)
			.phoneNumber(phoneNumber)
			.address(addressRequest.toDomain())
			.build();
	}
}
