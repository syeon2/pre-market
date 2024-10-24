package io.syeony.premarket.order.infrastructure;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.order.domain.model.Order;
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
	public String createNormalOrder(final Order order) {
		OrderEntity savedEntity = orderRepository.save(orderMapper.toEntity(order));
		order.getNormalOrderDetails().forEach(domain -> {
			orderDetailRepository.save(orderDetailMapper.toEntity(domain.withOrderId(savedEntity.getOrderId())));
		});
		return savedEntity.getOrderId();
	}

	@Override
	public String createPreOrder(final Order order) {
		OrderEntity savedEntity = orderRepository.save(orderMapper.toEntity(order));
		orderDetailRepository.save(
			orderDetailMapper.toEntity(order.getPreOrderDetail().withOrderId(savedEntity.getOrderId())));
		return savedEntity.getOrderId();
	}
}
