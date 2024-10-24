package io.syeony.premarket.order.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.order.domain.processor.CreateOrderProcessor;
import io.syeony.premarket.order.domain.processor.CreatePreOrderProcessor;

@Import({
	CreateOrderProcessor.class,
	CreatePreOrderProcessor.class
})
@Configuration
public class OrderModuleConfiguration {
}
