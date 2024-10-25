package io.syeony.premarket.order.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.order.application.OrderFacade;
import io.syeony.premarket.order.presentation.request.CreateNormalOrderRequest;
import io.syeony.premarket.order.presentation.request.CreatePreOrderRequest;
import io.syeony.premarket.order.presentation.request.OrderDetailRequest;
import io.syeony.premarket.order.presentation.request.PreOrderDetailRequest;
import io.syeony.premarket.order.presentation.request.ShippingAddressRequest;

class OrderCommandApiTest extends ControllerTestSupport {

	@Mock
	private OrderFacade orderFacade = mock(OrderFacade.class);

	@Override
	protected Object initController() {
		return new OrderCommandApi(orderFacade);
	}

	@Test
	@DisplayName(value = "Create normal order successfully")
	void createNormalOrder() throws Exception {
		// given
		CreateNormalOrderRequest request = new CreateNormalOrderRequest(
			List.of(new OrderDetailRequest(1L, 10)),
			new ShippingAddressRequest("address1", "address2", "12345"),
			"memberId"
		);

		given(orderFacade.createNormalOrder(any())).willReturn("order_id");

		// when // then
		mockMvc.perform(
				post("/api/v1/orders/normal")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.order_id").isString())
			.andDo(document("order-create-normal",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("order_details[].item_no").type(JsonFieldType.NUMBER).description("주문 상품 고유번호"),
					fieldWithPath("order_details[].quantity").type(JsonFieldType.NUMBER).description("주문 상품 수량"),
					fieldWithPath("shipping_address.base_address").type(JsonFieldType.STRING).description("배송정보 1"),
					fieldWithPath("shipping_address.address_detail").type(JsonFieldType.STRING).description("배송정보 2"),
					fieldWithPath("shipping_address.zipcode").type(JsonFieldType.STRING).description("우편 번호"),
					fieldWithPath("member_id").type(JsonFieldType.STRING).description("주문자 회원 아이디")
				),
				relaxedResponseFields(
					fieldWithPath("data.order_id").type(JsonFieldType.STRING).description("주문 아이디")
				)
			));
	}

	@Test
	@DisplayName(value = "Create pre order successfully")
	void createPreOrder() throws Exception {
		// given
		CreatePreOrderRequest request = new CreatePreOrderRequest(
			new PreOrderDetailRequest(1L, 10),
			new ShippingAddressRequest("address1", "address2", "12345"),
			"memberId"
		);

		given(orderFacade.createPreOrder(any())).willReturn("order_id");

		// when // then
		mockMvc.perform(
				post("/api/v1/orders/pre")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.order_id").isString())
			.andDo(document("order-create-pre",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("pre_order_detail.item_no").type(JsonFieldType.NUMBER).description("주문 상품 고유번호"),
					fieldWithPath("pre_order_detail.quantity").type(JsonFieldType.NUMBER).description("주문 상품 수량"),
					fieldWithPath("shipping_address.base_address").type(JsonFieldType.STRING).description("배송정보 1"),
					fieldWithPath("shipping_address.address_detail").type(JsonFieldType.STRING).description("배송정보 2"),
					fieldWithPath("shipping_address.zipcode").type(JsonFieldType.STRING).description("우편 번호"),
					fieldWithPath("member_id").type(JsonFieldType.STRING).description("주문자 회원 아이디")
				),
				relaxedResponseFields(
					fieldWithPath("data.order_id").type(JsonFieldType.STRING).description("주문 아이디")
				)
			));
	}
}
