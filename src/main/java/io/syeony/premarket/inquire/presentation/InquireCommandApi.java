package io.syeony.premarket.inquire.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.inquire.application.InquireFacade;
import io.syeony.premarket.inquire.presentation.request.CreateInquireCommentRequest;
import io.syeony.premarket.inquire.presentation.request.CreateInquireRequest;
import io.syeony.premarket.inquire.presentation.request.DeleteInquireRequest;
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
public final class InquireCommandApi {

	private final InquireFacade inquireFacade;

	@PostMapping("/v1/items/inquires")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<Void> createInquire(
		@RequestBody @Valid CreateInquireRequest request
	) {
		inquireFacade.createInquire(request.toDomain());
		return ApiResult.success();
	}

	@DeleteMapping("/v1/items/inquires/{inquire_no}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Void> deleteInquire(
		@PathVariable(value = "inquire_no") Long inquireNo,
		@RequestBody @Valid DeleteInquireRequest request
	) {
		inquireFacade.deleteInquire(inquireNo, request.itemNo(), request.memberId());
		return ApiResult.success();
	}

	@PostMapping("/v1/items/inquires/comment")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Void> createInquireComment(
		@RequestBody @Valid CreateInquireCommentRequest request
	) {
		inquireFacade.createInquireComment(request.toDomain());
		return ApiResult.success();
	}

	@DeleteMapping("/v1/items/inquires/{inquire_no}/comment/{comment_no}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<Void> deleteInquireComment(
		@PathVariable(value = "inquire_no") Long inquireNo,
		@PathVariable(value = "comment_no") Long commentNo
	) {
		inquireFacade.deleteInquireComment(inquireNo, commentNo);
		return ApiResult.success();
	}
}
