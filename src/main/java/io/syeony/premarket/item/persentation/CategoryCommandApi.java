package io.syeony.premarket.item.persentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.CategoryFacade;
import io.syeony.premarket.item.persentation.request.CreateCategoryRequest;
import io.syeony.premarket.support.common.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
	value = "/api",
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public final class CategoryCommandApi {

	private final CategoryFacade categoryFacade;

	@PostMapping("/v1/categories")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Void> createCategory(
		@RequestBody @Valid CreateCategoryRequest request
	) {
		categoryFacade.createCategory(request.categoryName());
		return ApiResult.success();
	}

	@DeleteMapping(value = "/v1/categories/{category_no}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Void> deleteCategory(
		@PathVariable(value = "category_no") Long categoryNo
	) {
		categoryFacade.deleteCategory(categoryNo);
		return ApiResult.success();
	}
}
