package io.syeony.premarket.item.persentation.request;

import org.springframework.core.convert.converter.Converter;

import io.syeony.premarket.item.persentation.request.vo.ItemTypeRequest;

public class StringToItemTypeConverter implements Converter<String, ItemTypeRequest> {
	@Override
	public ItemTypeRequest convert(String source) {
		return ItemTypeRequest.valueOf(source.toUpperCase());
	}
}
