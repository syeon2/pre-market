package io.syeony.premarket.order.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.order.application.OrderFacade;
import io.syeony.premarket.order.presentation.request.CreateNormalOrderRequest;
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
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<CreateOrderResponse> createOrder(
		@RequestBody @Valid CreateNormalOrderRequest request
	) {
		var orderId = orderFacade.createNormalOrder(request.toDomain());
		return ApiResult.success(new CreateOrderResponse(orderId));
	}

	@PostMapping("/v1/orders/pre")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<CreateOrderResponse> createPreOrder(
		@RequestBody @Valid CreatePreOrderRequest request
	) {
		var orderId = orderFacade.createPreOrder(request.toDomain());
		return ApiResult.success(new CreateOrderResponse(orderId));
	}
}
