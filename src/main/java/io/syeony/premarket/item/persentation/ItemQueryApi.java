package io.syeony.premarket.item.persentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.persentation.response.RetrieveCategoryItemResponse;
import io.syeony.premarket.item.persentation.response.RetrieveItemDetailResponse;
import io.syeony.premarket.item.persentation.response.RetrieveRegisteredItemResponse;
import io.syeony.premarket.support.common.ApiResult;
import io.syeony.premarket.support.common.PageConverter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
	value = "/api",
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ItemQueryApi {

	private final ItemFacade itemFacade;

	@GetMapping("/v1/members/{memberId}/registered_items")
	public ResponseEntity<ApiResult<Page<RetrieveRegisteredItemResponse>>> retrieveRegisteredItems(
		@PathVariable String memberId, Pageable pageable
	) {
		Page<Item> items = itemFacade.retrieveRegisteredItems(memberId, pageable);
		return ResponseEntity.ok()
			.body(ApiResult.ok(PageConverter.convert(items, RetrieveRegisteredItemResponse::from)));
	}

	@GetMapping("/v1/items")
	public ResponseEntity<ApiResult<Page<RetrieveCategoryItemResponse>>> retrieveCategoryItems(
		@RequestParam(value = "category_id") Long categoryId, Pageable pageable
	) {
		Page<Item> items = itemFacade.retrieveCategoryItems(categoryId, pageable);
		return ResponseEntity.ok()
			.body(ApiResult.ok(PageConverter.convert(items, RetrieveCategoryItemResponse::from)));
	}

	@GetMapping("/v1/items/{itemId}")
	public ResponseEntity<ApiResult<RetrieveItemDetailResponse>> retrieveItemDetail(
		@PathVariable Long itemId
	) {
		Item item = itemFacade.retrieveItemDetail(itemId);
		return ResponseEntity.ok()
			.body(ApiResult.ok(RetrieveItemDetailResponse.from(item)));
	}
}
