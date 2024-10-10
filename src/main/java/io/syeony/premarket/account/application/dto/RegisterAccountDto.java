package io.syeony.premarket.account.application.dto;

import lombok.Builder;

@Builder
public record RegisterAccountDto(

	String email,
	String rawPassword,
	String name,
	String phoneNumber,
	Address address
) {
	public record Address(
		String address1,
		String address2,
		String zipcode
	) {
	}
}
