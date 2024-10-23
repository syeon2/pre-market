package io.syeony.premarket.item.persentation.response;

import java.util.List;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.persentation.response.vo.CategoryResponse;

public record RetrieveCategoriesResponse(
	List<CategoryResponse> categories
) {
	public static RetrieveCategoriesResponse from(List<Category> categories) {
		List<CategoryResponse> response = categories.stream()
			.map(domain -> new CategoryResponse(domain.getNo(), domain.getName()))
			.toList();

		return new RetrieveCategoriesResponse(response);
	}

}
