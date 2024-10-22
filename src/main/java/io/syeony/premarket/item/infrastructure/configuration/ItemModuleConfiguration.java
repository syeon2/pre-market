package io.syeony.premarket.item.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.item.domain.processor.RegisterItemProcessor;

@Configuration
@Import({
	RegisterItemProcessor.class,
})
public class ItemModuleConfiguration {
}
