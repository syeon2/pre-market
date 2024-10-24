package io.syeony.premarket.order.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.processor.CreateOrderProcessor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {

	private final CreateOrderProcessor createOrderProcessor;

	@Transactional
	public String createOrder(final Order order) {
		return createOrderProcessor.createOrder(order);
	}
}
