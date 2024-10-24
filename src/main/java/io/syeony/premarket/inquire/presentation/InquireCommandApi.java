package io.syeony.premarket.inquire.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.inquire.application.InquireFacade;
import io.syeony.premarket.inquire.presentation.request.CreateInquireRequest;
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

	@PostMapping("/v1/items/{item_id}/inquires")
	public ResponseEntity<Void> createInquire(
		@PathVariable(value = "item_id") String itemId,
		@RequestBody @Valid CreateInquireRequest request
	) {
		inquireFacade.createInquire(itemId, request.toDomain());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
