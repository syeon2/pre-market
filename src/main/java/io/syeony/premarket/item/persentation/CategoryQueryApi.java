package io.syeony.premarket.item.persentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.CategoryFacade;
import io.syeony.premarket.item.persentation.response.RetrieveCategoriesResponse;
import io.syeony.premarket.support.common.ApiResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
	value = "/api",
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class CategoryQueryApi {

	private final CategoryFacade categoryFacade;

	@GetMapping("/v1/categories")
	public ResponseEntity<ApiResult<RetrieveCategoriesResponse>> retrieveAllCategories() {
		return ResponseEntity.ok()
			.body(ApiResult.ok(RetrieveCategoriesResponse.from(categoryFacade.retrieveAllCategories())));
	}
}
