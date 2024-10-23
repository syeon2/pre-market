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
public final class ItemQueryApi {

	private final ItemFacade itemFacade;

	@GetMapping("/v1/members/{memberId}/registered_items")
	public ResponseEntity<ApiResult<Page<RetrieveRegisteredItemResponse>>> findRegisteredItems(
		@PathVariable String memberId, Pageable pageable
	) {
		Page<Item> items = itemFacade.findRegisteredItems(memberId, pageable);
		return ResponseEntity.ok()
			.body(ApiResult.ok(PageConverter.convert(items, RetrieveRegisteredItemResponse::from)));
	}

	@GetMapping("/v1/items")
	public ResponseEntity<ApiResult<Page<RetrieveCategoryItemResponse>>> findCategoryItems(
		@RequestParam(value = "category_no") Long categoryNo, Pageable pageable
	) {
		Page<Item> items = itemFacade.retrieveCategoryItems(categoryNo, pageable);
		return ResponseEntity.ok()
			.body(ApiResult.ok(PageConverter.convert(items, RetrieveCategoryItemResponse::from)));
	}

	@GetMapping("/v1/items/{item_no}")
	public ResponseEntity<ApiResult<RetrieveItemDetailResponse>> retrieveItemDetail(
		@PathVariable(value = "item_no") Long itemNo
	) {
		Item item = itemFacade.retrieveItemDetail(itemNo);
		return ResponseEntity.ok()
			.body(ApiResult.ok(RetrieveItemDetailResponse.from(item)));
	}
}
