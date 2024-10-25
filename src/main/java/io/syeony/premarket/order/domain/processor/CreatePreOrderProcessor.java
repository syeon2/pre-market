package io.syeony.premarket.order.domain.processor;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.model.OrderDetail;
import io.syeony.premarket.order.domain.processor.reader.OrderWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePreOrderProcessor {

	private final OrderWriter orderWriter;
	private final ItemReader itemReader;

	public String createOrder(final Order order) {
		var orderDetail = createPreOrderDetail(order.getPreOrderDetail());
		return orderWriter.createPreOrder(order.initializeForCreate(orderDetail));
	}

	private OrderDetail createPreOrderDetail(OrderDetail orderDetail) {
		var item = itemReader.findItemByNo(orderDetail.getItemNo())
			.orElseThrow(() -> new IllegalArgumentException("Not found item"));
		validateItem(item);

		return orderDetail.initializeForCreate(item.getCost().getPrice(), item.getCost().getDiscount());
	}

	private void validateItem(Item item) {
		if (!item.isPreOrderType()) {
			throw new IllegalArgumentException("The order type must be pre-order type");
		}
		if (item.isPreOrderAvailable()) {
			throw new IllegalArgumentException("Pre-order schedule must be after now");
		}
	}
}
