package io.syeony.premarket.order.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.order.application.OrderFacade;
import io.syeony.premarket.order.presentation.request.CreateOrderRequest;
import io.syeony.premarket.order.presentation.request.CreatePreOrderRequest;
import io.syeony.premarket.order.presentation.response.CreateOrderResponse;
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
public final class OrderCommandApi {

	private final OrderFacade orderFacade;

	@PostMapping("/v1/orders/normal")
	public ResponseEntity<ApiResult<CreateOrderResponse>> createOrder(
		@RequestBody @Valid CreateOrderRequest request
	) {
		var orderId = orderFacade.createOrder(request.toDomain());
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResult.ok(new CreateOrderResponse(orderId)));
	}

	@PostMapping("/v1/orders/pre")
	public ResponseEntity<ApiResult<String>> createPreOrder(
		@RequestBody @Valid CreatePreOrderRequest request
	) {
		var orderId = orderFacade.createPreOrder(request.toDomain());
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResult.ok(orderId));
	}
}
