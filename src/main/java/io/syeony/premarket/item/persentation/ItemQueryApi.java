package io.syeony.premarket.item.persentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.persentation.response.FindCategoryItemResponse;
import io.syeony.premarket.item.persentation.response.FindRegisteredItemResponse;
import io.syeony.premarket.item.persentation.response.RetrieveItemDetailResponse;
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
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Page<FindRegisteredItemResponse>> findRegisteredItems(
		@PathVariable String memberId, Pageable pageable
	) {
		var items = itemFacade.findRegisteredItems(memberId, pageable);
		return ApiResult.success(PageConverter.convert(items, FindRegisteredItemResponse::from));
	}

	@GetMapping("/v1/items")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Page<FindCategoryItemResponse>> findCategoryItems(
		@RequestParam(value = "category_no") Long categoryNo, Pageable pageable
	) {
		var items = itemFacade.findCategoryItems(categoryNo, pageable);
		return ApiResult.success(PageConverter.convert(items, FindCategoryItemResponse::from));
	}

	@GetMapping("/v1/items/{item_no}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<RetrieveItemDetailResponse> retrieveItemDetail(
		@PathVariable(value = "item_no") Long itemNo
	) {
		var item = itemFacade.retrieveItemDetail(itemNo);
		return ApiResult.success(RetrieveItemDetailResponse.from(item));
	}
}
