package io.syeony.premarket.item.persentation.response.vo;

import io.syeony.premarket.item.domain.model.Category;

public record CategoryResponse(
	Long id,
	String name
) {
	public static CategoryResponse from(Category category) {
		return new CategoryResponse(category.getNo(), category.getName());
	}
}
