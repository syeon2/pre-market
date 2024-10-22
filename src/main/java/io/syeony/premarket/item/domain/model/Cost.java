package io.syeony.premarket.item.domain.model;

import lombok.Getter;

@Getter
public class Cost {

	private Integer price;
	private Integer discount;

	public Cost(Integer price, Integer discount) {
		this.price = price;
		this.discount = discount;
	}
}
