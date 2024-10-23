package io.syeony.premarket.item.persentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.persentation.request.DeactivateItemRequest;
import io.syeony.premarket.item.persentation.request.EditItemRequest;
import io.syeony.premarket.item.persentation.request.RegisterItemRequest;
import io.syeony.premarket.item.persentation.response.RegisterItemResponse;
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
public final class ItemCommandApi {

	private final ItemFacade itemFacade;

	@PostMapping("/v1/items")
	public ResponseEntity<ApiResult<RegisterItemResponse>> registerItem(
		@RequestBody @Valid RegisterItemRequest request
	) {
		var itemId = itemFacade.registerItem(request.toDomain());
		return ResponseEntity.ok()
			.body(ApiResult.ok(new RegisterItemResponse(itemId)));
	}

	@PatchMapping("/v1/items/deactivate")
	public ResponseEntity<ApiResult<Void>> deactivateItem(
		@RequestBody @Valid DeactivateItemRequest request
	) {
		itemFacade.deactivateItem(request.sellerId(), request.itemId());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/v1/items/{item_id}/info")
	public ResponseEntity<ApiResult<Void>> editItem(
		@PathVariable("item_id") String itemId,
		@RequestBody EditItemRequest request
	) {
		itemFacade.editItem(itemId, request.toDomain());
		return ResponseEntity.ok().build();
	}
}
