package io.syeony.premarket.account.application.dto;

import io.syeony.premarket.account.domain.model.vo.Address;

public record AddressDto(
	String baseAddress,
	String addressDetail,
	String zipcode
) {
	public Address toDomain() {
		return new Address(baseAddress, addressDetail, zipcode);
	}
}
