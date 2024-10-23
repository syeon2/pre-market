package io.syeony.premarket.item.persentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
	@JsonProperty(value = "category_name")
	@NotBlank(message = "The category name field is required")
	String categoryName
) {
}
