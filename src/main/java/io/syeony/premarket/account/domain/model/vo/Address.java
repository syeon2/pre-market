package io.syeony.premarket.account.domain.model.vo;

public record Address(
	String baseAddress,
	String addressDetails,
	String zipcode
) {
}
