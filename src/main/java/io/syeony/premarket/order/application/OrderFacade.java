package io.syeony.premarket.order.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.processor.CreateOrderProcessor;
import io.syeony.premarket.order.domain.processor.CreatePreOrderProcessor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {

	private final CreateOrderProcessor createOrderProcessor;
	private final CreatePreOrderProcessor createPreOrderProcessor;

	@Transactional
	public String createOrder(final Order order) {
		return createOrderProcessor.createOrder(order);
	}

	public String createPreOrder(final Order domain) {
		return createPreOrderProcessor.createOrder(domain);
	}
}
