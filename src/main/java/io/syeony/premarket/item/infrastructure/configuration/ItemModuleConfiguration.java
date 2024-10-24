package io.syeony.premarket.item.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.item.domain.processor.DeactivateItemProcessor;
import io.syeony.premarket.item.domain.processor.EditItemProcessor;
import io.syeony.premarket.item.domain.processor.RegisterItemProcessor;

@Configuration
@Import({
	RegisterItemProcessor.class,
	DeactivateItemProcessor.class,
	EditItemProcessor.class
})
public class ItemModuleConfiguration {
}
