package io.syeony.premarket.item.persentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.CategoryFacade;
import io.syeony.premarket.item.persentation.request.CreateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
	value = "/api",
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class CategoryCommandApi {

	private final CategoryFacade categoryFacade;

	@PostMapping("/v1/categories")
	public ResponseEntity<Void> createCategory(
		@RequestBody @Valid CreateCategoryRequest request
	) {
		categoryFacade.createCategory(request.name());
		return ResponseEntity
			.ok()
			.build();
	}

	@DeleteMapping(value = "/v1/categories/{categoryId}")
	public ResponseEntity<Void> deleteCategory(
		@PathVariable Long categoryId
	) {
		categoryFacade.deleteCategory(categoryId);
		return ResponseEntity
			.ok()
			.build();
	}
}