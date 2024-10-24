package io.syeony.premarket.inquire.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.inquire.application.InquireFacade;
import io.syeony.premarket.inquire.presentation.request.CreateInquireCommentRequest;
import io.syeony.premarket.inquire.presentation.request.CreateInquireRequest;
import io.syeony.premarket.inquire.presentation.request.DeleteInquireRequest;
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
	public ResponseEntity<Void> createInquire(
		@RequestBody @Valid CreateInquireRequest request
	) {
		inquireFacade.createInquire(request.toDomain());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/v1/items/inquires/{inquire_no}")
	public ResponseEntity<Void> deleteInquire(
		@PathVariable(value = "inquire_no") Long inquireNo,
		@RequestBody @Valid DeleteInquireRequest request
	) {
		inquireFacade.deleteInquire(inquireNo, request.itemNo(), request.memberId());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/v1/items/inquires/comment")
	public ResponseEntity<Void> createInquireComment(
		@RequestBody @Valid CreateInquireCommentRequest request
	) {
		inquireFacade.createInquireComment(request.toDomain());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/v1/items/inquires/{inquire_no}/comment/{comment_no}")
	public ResponseEntity<Void> deleteInquireComment(
		@PathVariable(value = "inquire_no") Long inquireNo,
		@PathVariable(value = "comment_no") Long commentNo
	) {
		inquireFacade.deleteInquireComment(inquireNo, commentNo);
		return ResponseEntity.ok().build();
	}
}
