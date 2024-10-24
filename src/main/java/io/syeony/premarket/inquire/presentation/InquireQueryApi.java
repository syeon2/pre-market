package io.syeony.premarket.inquire.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.inquire.application.InquireFacade;
import io.syeony.premarket.inquire.presentation.response.FindInquireResponse;
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
public final class InquireQueryApi {

	private final InquireFacade inquireFacade;

	@GetMapping("/v1/items/{item_no}/inquires")
	public ResponseEntity<ApiResult<Page<FindInquireResponse>>> findItemInquires(
		@PathVariable(value = "item_no") Long itemNo, Pageable pageable
	) {
		var inquires = inquireFacade.findItemInquires(itemNo, pageable);
		return ResponseEntity.ok()
			.body(ApiResult.ok(PageConverter.convert(inquires, FindInquireResponse::from)));
	}
}
