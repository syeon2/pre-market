package io.syeony.premarket.item.persentation.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
	@NotBlank(message = "The name field is required")
	String name
) {
}
