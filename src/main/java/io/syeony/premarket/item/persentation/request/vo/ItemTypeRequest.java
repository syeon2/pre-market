package io.syeony.premarket.item.persentation.request.vo;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.syeony.premarket.item.domain.model.ItemType;

public enum ItemTypeRequest {
	PRE_ORDER,
	NORMAL_ORDER;

	@JsonCreator
	public static ItemTypeRequest fromString(String value) {
		try {
			return ItemTypeRequest.valueOf(value.toUpperCase());
		} catch (Exception exception) {
			throw new IllegalArgumentException("Invalid item type field: " + value);
		}
	}

	public ItemType toDomain() {
		if (this.equals(PRE_ORDER)) {
			return ItemType.PRE_ORDER;
		}

		return ItemType.NORMAL_ORDER;
	}
}
