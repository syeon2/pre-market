package io.syeony.premarket.support.common;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public final class PageConverter {
	public static <T, R> Page<R> convert(Page<T> contents, Function<T, R> converter) {
		List<R> convertedContents = contents.getContent().stream()
			.map(converter)
			.toList();

		return new PageImpl<>(convertedContents, contents.getPageable(), contents.getTotalPages());
	}
}
