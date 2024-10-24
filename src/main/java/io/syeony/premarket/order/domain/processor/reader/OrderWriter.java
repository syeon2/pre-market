package io.syeony.premarket.order.domain.processor.reader;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.model.OrderDetail;

public interface OrderWriter {

	String createNormalOrder(Order order);

	String createPreOrder(Order initializeOrder, OrderDetail orderDetail);
}
