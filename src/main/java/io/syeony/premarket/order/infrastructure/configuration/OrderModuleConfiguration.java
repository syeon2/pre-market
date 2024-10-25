package io.syeony.premarket.order.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.order.domain.processor.CreateNormalOrderProcessor;
import io.syeony.premarket.order.domain.processor.CreatePreOrderProcessor;

@Import({
	CreateNormalOrderProcessor.class,
	CreatePreOrderProcessor.class
})
@Configuration
public class OrderModuleConfiguration {
}
