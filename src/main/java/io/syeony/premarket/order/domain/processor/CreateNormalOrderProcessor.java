package io.syeony.premarket.order.domain.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.model.OrderDetail;
import io.syeony.premarket.order.domain.processor.reader.OrderWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateNormalOrderProcessor {

	private final OrderWriter orderWriter;
	private final ItemReader itemReader;

	public String createOrder(final Order order) {
		var orderDetails = createOrderDetails(order.extractItemNos(), order.getNormalOrderDetails());
		return orderWriter.createNormalOrder(order.initializeForCreate(orderDetails));
	}

	private List<OrderDetail> createOrderDetails(List<Long> itemNos, List<OrderDetail> orderDetails) {
		var itemMap = convertItemListToMap(itemNos);

		return orderDetails.stream()
			.map(domain -> initializeOrderDetail(domain, itemMap))
			.collect(Collectors.toList());
	}

	private Map<Long, Item> convertItemListToMap(List<Long> itemNos) {
		return itemReader.findItemsByNos(itemNos).stream()
			.collect(Collectors.toMap(Item::getItemNo, domain -> domain));
	}

	private OrderDetail initializeOrderDetail(OrderDetail orderDetail, Map<Long, Item> itemMap) {
		var findItem = itemMap.get(orderDetail.getItemNo());
		validateOrderType(findItem);

		return orderDetail.initializeForCreate(
			findItem.getCost().getPrice(),
			findItem.getCost().getDiscount());
	}

	private void validateOrderType(Item findItem) {
		if (!findItem.isNormalOrderType()) {
			throw new IllegalArgumentException("The order type must be NORMAL_ORDER");
		}
	}
}
