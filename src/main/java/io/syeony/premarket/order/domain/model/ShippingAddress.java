package io.syeony.premarket.order.domain.model;

public record ShippingAddress(
	String baseAddress,
	String addressDetails,
	String zipcode
) {
}
