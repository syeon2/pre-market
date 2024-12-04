package io.syeony.premarket.member.domain.model.vo;

public record Address(
	String baseAddress,
	String addressDetails,
	String zipcode
) {
}
