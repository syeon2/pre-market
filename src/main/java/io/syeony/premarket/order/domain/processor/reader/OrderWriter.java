package io.syeony.premarket.order.domain.processor.reader;

import java.util.List;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.model.OrderDetail;

public interface OrderWriter {

	String create(Order order, List<OrderDetail> orderDetails);
}
