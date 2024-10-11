package io.syeony.premarket.account.presentation.request;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.account.application.dto.AddressDto;
import io.syeony.premarket.account.application.dto.RegisterAccountDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterAccountRequest(
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
	Address address,

	@JsonProperty(value = "verification_code")
	@NotBlank(message = "The verification code is required")
	String verificationCode
) {
	public record Address(
		@JsonProperty(value = "base_address")
		@NotBlank(message = "The address1 field is required")
		String baseAddress,

		@JsonProperty(value = "address_detail")
		@NotBlank(message = "The address2 field is required")
		String addressDetail,

		@NotBlank(message = "The zipcode field is required")
		String zipcode
	) {
	}

	public RegisterAccountDto toRegisterAccountDto() {
		return RegisterAccountDto.builder()
			.email(email)
			.rawPassword(password)
			.name(name)
			.phoneNumber(phoneNumber)
			.address(new AddressDto(address.baseAddress, address().addressDetail, address().zipcode))
			.build();
	}
}
