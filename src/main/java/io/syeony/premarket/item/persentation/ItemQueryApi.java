package io.syeony.premarket.item.persentation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.application.dto.RetrieveRegisteredItemDto;
import io.syeony.premarket.item.persentation.response.RetrieveRegisteredItemResponse;
import io.syeony.premarket.support.common.ApiResult;
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

	@GetMapping("/v1/items")
	public ResponseEntity<ApiResult<List<RetrieveRegisteredItemResponse>>> retrieveRegisteredItems(
		@RequestParam(value = "member_id", required = true) String memberId,
		Pageable pageable
	) {
		List<RetrieveRegisteredItemDto> items = itemFacade.retrieveRegisteredItems(memberId, pageable);

		return ResponseEntity
			.ok()
			.body(ApiResult.ok(RetrieveRegisteredItemResponse.from(items)));
	}
}
