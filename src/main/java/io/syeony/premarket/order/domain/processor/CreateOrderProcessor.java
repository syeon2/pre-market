package io.syeony.premarket.order.domain.processor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.item.domain.processor.reader.ItemReader;
import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.model.OrderDetail;
import io.syeony.premarket.order.domain.processor.reader.OrderWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateOrderProcessor {

	private final OrderWriter orderWriter;
	private final ItemReader itemReader;

	public String createOrder(final Order order) {
		List<OrderDetail> orderDetails = createOrderDetails(order.getOrderDetails());
		Order initializeOrder = order.initializeForCreate(orderDetails);

		return orderWriter.create(initializeOrder, orderDetails);
	}

	private List<OrderDetail> createOrderDetails(List<OrderDetail> orderDetails) {
		Set<OrderDetail> orderDetailSet = new HashSet<>();

		Map<Long, Item> itemMap = itemReader.findItemsByNos(extractItemNos(orderDetails)).stream()
			.collect(Collectors.toMap(Item::getItemNo, domain -> domain));

		for (OrderDetail orderDetail : orderDetails) {
			Item findItem = itemMap.get(orderDetail.getItemNo());

			if (findItem.getItemType().equals(ItemType.PRE_ORDER)) {
				throw new IllegalArgumentException("The order type must be NORMAL_ORDER");
			}

			OrderDetail initializeOrderDetail = orderDetail.initializeForCreate(
				findItem.getCost().getPrice(),
				findItem.getCost().getDiscount());

			orderDetailSet.add(initializeOrderDetail);
		}

		return orderDetailSet.stream().toList();
	}

	private List<Long> extractItemNos(List<OrderDetail> orderDetails) {
		return orderDetails.stream()
			.map(OrderDetail::getItemNo)
			.toList();
	}
}
