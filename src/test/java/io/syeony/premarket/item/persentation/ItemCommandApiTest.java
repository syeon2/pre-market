package io.syeony.premarket.item.persentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.persentation.request.DeactivateItemRequest;
import io.syeony.premarket.item.persentation.request.EditItemRequest;
import io.syeony.premarket.item.persentation.request.RegisterItemRequest;
import io.syeony.premarket.item.persentation.request.vo.CostRequest;
import io.syeony.premarket.item.persentation.request.vo.ItemTypeRequest;
import io.syeony.premarket.item.persentation.request.vo.PreOrderScheduleRequest;

class ItemCommandApiTest extends ControllerTestSupport {

	private final ItemFacade itemFacade = mock(ItemFacade.class);

	@Override
	protected Object initController() {
		return new ItemCommandApi(itemFacade);
	}

	@Test
	@DisplayName(value = "Add Item successfully when all required fields are provided")
	void registerItemApi() throws Exception {
		// given
		RegisterItemRequest request = createRegisterItemRequest("memberId");

		given(itemFacade.registerItem(request.toDomain())).willReturn(1L);

		// when // then
		mockMvc.perform(
				post("/api/v1/items")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.item_no").isNumber())
			.andDo(document("item-add",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("item_name").type(JsonFieldType.STRING).description("상품 이름"),
					fieldWithPath("cost.price").type(JsonFieldType.NUMBER).description("상품 가격"),
					fieldWithPath("cost.discount").type(JsonFieldType.NUMBER).description("상품 할인 금액"),
					fieldWithPath("stock").type(JsonFieldType.NUMBER).description("상품 재고"),
					fieldWithPath("introduction").type(JsonFieldType.STRING).description("상품 설명"),
					fieldWithPath("item_type").type(JsonFieldType.STRING).description("상품 타입"),
					fieldWithPath("pre_order_schedule.year").type(JsonFieldType.NUMBER).description("예약 주문 연"),
					fieldWithPath("pre_order_schedule.month").type(JsonFieldType.NUMBER).description("예약 주문 월"),
					fieldWithPath("pre_order_schedule.date").type(JsonFieldType.NUMBER).description("예약 주문 일"),
					fieldWithPath("pre_order_schedule.hour").type(JsonFieldType.NUMBER).description("예약 주문 시간"),
					fieldWithPath("pre_order_schedule.minute").type(JsonFieldType.NUMBER).description("예약 주문 분"),
					fieldWithPath("seller_id").type(JsonFieldType.STRING).description("판매 회원 아이디"),
					fieldWithPath("category_no").type(JsonFieldType.NUMBER).description("카테고리 고유번호")
				),
				responseFields(
					fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태 코드"),
					fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
					fieldWithPath("data.item_no").type(JsonFieldType.NUMBER).description("상품 번호")
				)
			));
	}

	@Test
	@DisplayName(value = "Deactivate item successfully when all required fields are provided")
	void deactivateItemApi() throws Exception {
		// given
		DeactivateItemRequest request = new DeactivateItemRequest("sellerId", "itemId");

		// when // then
		mockMvc.perform(
				patch("/api/v1/items/deactivate")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("item-deactivate",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("seller_id").type(JsonFieldType.STRING).description("판매 회원 아이디"),
					fieldWithPath("item_id").type(JsonFieldType.STRING).description("상품 아이디")
				)
			));
	}

	@Test
	@DisplayName(value = "Edit item information successfully when all required fields are provied")
	void editItem() throws Exception {
		// given
		String itemId = "itemId";
		EditItemRequest request = createEditItemRequest();

		// when // then
		mockMvc.perform(
				patch("/api/v1/items/{item_id}/info", itemId)
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("item-edit",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("item_name").type(JsonFieldType.STRING).description("상품 이름"),
					fieldWithPath("cost.price").type(JsonFieldType.NUMBER).description("상품 정가"),
					fieldWithPath("cost.discount").type(JsonFieldType.NUMBER).description("상품 할인금액"),
					fieldWithPath("stock").type(JsonFieldType.NUMBER).description("상품 재고"),
					fieldWithPath("introduction").type(JsonFieldType.STRING).description("상품 설명"),
					fieldWithPath("category_no").type(JsonFieldType.NUMBER).description("상품 카테고리 고유번호"),
					fieldWithPath("seller_id").type(JsonFieldType.STRING).description("판매 회원 아이디")
				)
			));
	}

	private EditItemRequest createEditItemRequest() {
		return new EditItemRequest("changeName", new CostRequest(20000, 2000), 20,
			"changeIntro", 2L, "memberId");
	}

	private RegisterItemRequest createRegisterItemRequest(String memberId) {
		return new RegisterItemRequest("itemA",
			new CostRequest(10000, 1000),
			10,
			"hello introduce",
			ItemTypeRequest.PRE_ORDER,
			new PreOrderScheduleRequest(2025, 10, 24, 23, 49),
			memberId,
			1L
		);
	}
}
