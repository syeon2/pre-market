package io.syeony.premarket.order.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record ShippingAddressRequest(
	@JsonProperty(value = "base_address")
	@NotBlank(message = "The address1 field is required")
	String baseAddress,

	@JsonProperty(value = "address_detail")
	@NotBlank(message = "The address2 field is required")
	String addressDetail,

	@NotBlank(message = "The zipcode field is required")
	String zipcode
) {
	public String toString() {
		return baseAddress + "," + addressDetail + "," + zipcode;
	}
}
