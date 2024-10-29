package io.syeony.premarket.item.infrastructure.kafka;

import lombok.Getter;

@Getter
public class ItemStock {

	private Long itemNo;
	private Integer quantity;

	public ItemStock(Long itemNo, Integer quantity) {
		this.itemNo = itemNo;
		this.quantity = quantity;
	}

	public static final String ITEM_NO_FIELD = "itemNo";
	public static final String QUANTITY_FIELD = "quantity";
}
