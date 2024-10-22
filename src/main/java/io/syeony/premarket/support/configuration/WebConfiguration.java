package io.syeony.premarket.support.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.syeony.premarket.item.persentation.request.StringToItemTypeConverter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToItemTypeConverter());
	}
}
