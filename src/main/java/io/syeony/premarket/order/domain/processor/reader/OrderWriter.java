package io.syeony.premarket.order.domain.processor.reader;

import io.syeony.premarket.order.domain.model.Order;

public interface OrderWriter {

	String createNormalOrder(Order order);

	String createPreOrder(Order order);
}
