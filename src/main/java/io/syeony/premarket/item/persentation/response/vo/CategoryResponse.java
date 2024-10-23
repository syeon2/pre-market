package io.syeony.premarket.item.persentation.response.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Category;

public record CategoryResponse(
	@JsonProperty(value = "category_no")
	Long categoryNo,

	@JsonProperty(value = "category_name")
	String categoryName
) {
	public static CategoryResponse from(Category category) {
		return new CategoryResponse(category.getNo(), category.getName());
	}
}
