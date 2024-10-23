package io.syeony.premarket.item.persentation.response.vo;

import io.syeony.premarket.item.domain.model.Seller;

public record SellerResponse(
	String id,
	String name
) {
	public static SellerResponse from(Seller seller) {
		return new SellerResponse(seller.getMemberId(), seller.getName());
	}
}
