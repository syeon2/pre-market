package io.syeony.premarket.item.persentation.response.vo;

import io.syeony.premarket.item.domain.model.ItemType;

public enum ItemTypeResponse {
	PRE_ORDER,
	NORMAL_ORDER;

	public static ItemTypeResponse from(ItemType itemType) {
		if (itemType == ItemType.PRE_ORDER) {
			return PRE_ORDER;
		}
		return NORMAL_ORDER;
	}
}
