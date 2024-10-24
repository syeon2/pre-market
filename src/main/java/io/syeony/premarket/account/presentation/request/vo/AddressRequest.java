package io.syeony.premarket.account.presentation.request.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.account.domain.model.vo.Address;
import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
	@JsonProperty(value = "base_address")
	@NotBlank(message = "The address1 field is required")
	String baseAddress,

	@JsonProperty(value = "address_detail")
	@NotBlank(message = "The address2 field is required")
	String addressDetail,

	@NotBlank(message = "The zipcode field is required")
	String zipcode
) {
	public Address toDomain() {
		return new Address(baseAddress, addressDetail, zipcode);
	}
}
