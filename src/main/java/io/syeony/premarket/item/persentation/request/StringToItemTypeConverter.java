package io.syeony.premarket.item.persentation.request;

import org.springframework.core.convert.converter.Converter;

public class StringToItemTypeConverter implements Converter<String, AddItemRequest.ItemTypeRequest> {
	@Override
	public AddItemRequest.ItemTypeRequest convert(String source) {
		return AddItemRequest.ItemTypeRequest.valueOf(source.toUpperCase());
	}
}
