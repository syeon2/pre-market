package io.syeony.premarket.item.persentation.request;

import org.springframework.core.convert.converter.Converter;

public class StringToItemTypeConverter implements Converter<String, RegisterItemRequest.ItemTypeRequest> {
	@Override
	public RegisterItemRequest.ItemTypeRequest convert(String source) {
		return RegisterItemRequest.ItemTypeRequest.valueOf(source.toUpperCase());
	}
}
