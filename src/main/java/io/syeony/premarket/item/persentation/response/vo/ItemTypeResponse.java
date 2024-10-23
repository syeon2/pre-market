package io.syeony.premarket.item.persentation.response.vo;

import io.syeony.premarket.item.domain.model.ItemType;

public enum ItemTypeResponse {
	PRE_ORDER,
	NORMAL_ORDER;

	public static ItemTypeResponse from(ItemType itemType) {
		return switch (itemType) {
			case PRE_ORDER -> PRE_ORDER;
			default -> NORMAL_ORDER;
		};
	}
}
