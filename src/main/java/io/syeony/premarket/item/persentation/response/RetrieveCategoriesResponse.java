package io.syeony.premarket.item.persentation.response;

import java.util.List;

import io.syeony.premarket.item.domain.model.Category;
import lombok.Getter;

@Getter
public class RetrieveCategoriesResponse {
	private final List<CategoryResponse> categories;

	private RetrieveCategoriesResponse(List<CategoryResponse> categories) {
		this.categories = categories;
	}

	public static RetrieveCategoriesResponse from(List<Category> categories) {
		List<CategoryResponse> response = categories.stream()
			.map(domain -> new CategoryResponse(domain.getId(), domain.getName()))
			.toList();

		return new RetrieveCategoriesResponse(response);
	}

}
