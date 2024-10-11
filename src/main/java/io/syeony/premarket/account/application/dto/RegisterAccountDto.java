package io.syeony.premarket.account.application.dto;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.Password;
import lombok.Builder;

@Builder
public record RegisterAccountDto(

	String email,
	String rawPassword,
	String name,
	String phoneNumber,
	AddressDto address
) {
	public Account toDomain() {
		return Account.builder()
			.email(email)
			.password(Password.of(rawPassword))
			.name(name)
			.phoneNumber(phoneNumber)
			.address(address.toDomain())
			.build();
	}
}
