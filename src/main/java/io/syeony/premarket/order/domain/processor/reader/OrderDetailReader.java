package io.syeony.premarket.order.domain.processor.reader;

import java.util.List;

import io.syeony.premarket.order.domain.model.OrderDetail;

public interface OrderDetailReader {
	List<OrderDetail> findAllByNos(List<Long> itemNoList);
}
