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
		Item item = itemReader.findItemByNo(order.getPreOrderDetail().getItemNo())
			.orElseThrow(() -> new IllegalArgumentException("Not found item"));

		if (!item.isPreOrderType()) {
			throw new IllegalArgumentException("Order is not pre-order type");
		}

		OrderDetail orderDetail = OrderDetail.builder()
			.quantity(order.getPreOrderDetail().getQuantity())
			.totalPrice(order.getPreOrderDetail().calculateTotalPrice(item.getCost().getPrice()))
			.totalDiscount(order.getPreOrderDetail().calculateTotalDiscount(item.getCost().getDiscount()))
			.itemNo(item.getItemNo())
			.orderId(null)
			.build();

		Order initializeOrder = order.initializeForCreate(orderDetail);
		return orderWriter.createPreOrder(initializeOrder, orderDetail);
	}
}
