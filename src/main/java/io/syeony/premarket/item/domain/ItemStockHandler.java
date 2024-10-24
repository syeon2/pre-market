package io.syeony.premarket.item.domain;

public interface ItemStockHandler {

	void deductStock(Long itemNo, Integer quantity);

	void increaseStock(Long itemNo, Integer quantity);
}
