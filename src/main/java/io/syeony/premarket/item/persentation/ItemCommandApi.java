package io.syeony.premarket.item.persentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.persentation.request.AddItemRequest;
import io.syeony.premarket.item.persentation.response.AddItemResponse;
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
public class ItemCommandApi {

	private final ItemFacade itemFacade;

	@PostMapping("/v1/items")
	public ResponseEntity<ApiResult<AddItemResponse>> addItem(
		@RequestBody @Valid AddItemRequest request
	) {
		var itemId = itemFacade.addItem(request.toDto());
		return ResponseEntity
			.ok()
			.body(ApiResult.ok(new AddItemResponse(itemId)));
	}
}
