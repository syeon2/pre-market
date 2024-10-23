package io.syeony.premarket.item.persentation.response.vo;

import io.syeony.premarket.item.domain.model.Cost;

public record CostResponse(
	Integer price,
	Integer discount
) {
	public static CostResponse from(Cost cost) {
		return new CostResponse(cost.getPrice(), cost.getDiscount());
	}
}
