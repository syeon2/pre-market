package io.syeony.premarket.order.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.order.domain.model.Order;
import io.syeony.premarket.order.domain.processor.CreateNormalOrderProcessor;
import io.syeony.premarket.order.domain.processor.CreatePreOrderProcessor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {

	private final CreateNormalOrderProcessor createNormalOrderProcessor;
	private final CreatePreOrderProcessor createPreOrderProcessor;

	@Transactional
	public String createNormalOrder(final Order order) {
		return createNormalOrderProcessor.createOrder(order);
	}

	@Transactional
	public String createPreOrder(final Order order) {
		return createPreOrderProcessor.createOrder(order);
	}
}
