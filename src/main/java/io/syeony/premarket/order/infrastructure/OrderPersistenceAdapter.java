package io.syeony.premarket.order.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.model.OrderDetail;
import io.syeony.premarket.order.domain.processor.reader.OrderWriter;
import io.syeony.premarket.order.infrastructure.persistence.OrderDetailRepository;
import io.syeony.premarket.order.infrastructure.persistence.OrderRepository;
import io.syeony.premarket.order.infrastructure.persistence.entity.OrderEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderWriter {

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderMapper orderMapper;
	private final OrderDetailMapper orderDetailMapper;

	@Override
	public String create(Order order, List<OrderDetail> orderDetails) {
		OrderEntity savedOrderEntity = orderRepository.save(orderMapper.toEntity(order));
		orderDetails.forEach(domain -> {
			orderDetailRepository.save(orderDetailMapper.toEntity(domain, savedOrderEntity.getOrderId()));
		});

		return savedOrderEntity.getOrderId();
	}
}
