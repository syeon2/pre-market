package io.syeony.premarket.item.persentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.application.dto.RetrieveRegisteredItemDto;
import io.syeony.premarket.item.domain.model.ItemType;

class ItemQueryApiTest extends ControllerTestSupport {

	@Mock
	private ItemFacade itemFacade = mock(ItemFacade.class);

	@Override
	protected Object initController() {
		return new ItemQueryApi(itemFacade);
	}

	@Test
	@DisplayName(value = "Retrieve registered item when member id and pageable query parameters are provided")
	void retrieveRegisteredItemsApi() throws Exception {
		// given
		String memberId = "memberId";
		PageRequest pageRequest = PageRequest.of(0, 10);

		given(itemFacade.retrieveRegisteredItems(memberId, pageRequest))
			.willReturn(List.of(createRegisteredItemDto()));

		// when // then
		mockMvc.perform(
				get("/api/v1/items")
					.queryParam("member_id", memberId)
					.queryParam("page", "0")
					.queryParam("size", "10")
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].item_id").isNumber())
			.andExpect(jsonPath("$.data[0].item_name").isString())
			.andExpect(jsonPath("$.data[0].cost.price").isNumber())
			.andExpect(jsonPath("$.data[0].cost.discount").isNumber())
			.andExpect(jsonPath("$.data[0].stock").isNumber())
			.andExpect(jsonPath("$.data[0].item_type").isString())
			.andExpect(jsonPath("$.data[0].pre_order_schedule.year").isNumber())
			.andExpect(jsonPath("$.data[0].pre_order_schedule.month").isNumber())
			.andExpect(jsonPath("$.data[0].pre_order_schedule.date").isNumber())
			.andExpect(jsonPath("$.data[0].pre_order_schedule.hour").isNumber())
			.andExpect(jsonPath("$.data[0].pre_order_schedule.minute").isNumber())
			.andExpect(jsonPath("$.data[0].created_at").isString())
			.andExpect(jsonPath("$.data[0].category_id").isNumber())
			.andExpect(jsonPath("$.data[0].category_name").isString())
			.andDo(document("item-retrieve-registered",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				queryParameters(
					parameterWithName("member_id").description("회원 아이디"),
					parameterWithName("page").description("페이지 번호"),
					parameterWithName("size").description("한 페이지당 컨텐츠 수")
				),
				responseFields(
					fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태 코드"),
					fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
					fieldWithPath("data[].item_id").type(JsonFieldType.NUMBER).description("상품 고유번호"),
					fieldWithPath("data[].item_name").type(JsonFieldType.STRING).description("상품 이름"),
					fieldWithPath("data[].cost.price").type(JsonFieldType.NUMBER).description("상품 정가"),
					fieldWithPath("data[].cost.discount").type(JsonFieldType.NUMBER).description("상품 할인금액"),
					fieldWithPath("data[].stock").type(JsonFieldType.NUMBER).description("상품 재고"),
					fieldWithPath("data[].item_type").type(JsonFieldType.STRING).description("상품 타입"),
					fieldWithPath("data[].pre_order_schedule.year").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 연"),
					fieldWithPath("data[].pre_order_schedule.month").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 월"),
					fieldWithPath("data[].pre_order_schedule.date").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 일"),
					fieldWithPath("data[].pre_order_schedule.hour").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 시간"),
					fieldWithPath("data[].pre_order_schedule.minute").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 분"),
					fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("상품 등록일"),
					fieldWithPath("data[].category_id").type(JsonFieldType.NUMBER).description("카테고리 고유번호"),
					fieldWithPath("data[].category_name").type(JsonFieldType.STRING).description("카테고리 이름")
				)
			));
	}

	private static @NotNull RetrieveRegisteredItemDto createRegisteredItemDto() {
		return new RetrieveRegisteredItemDto(1L, "itemA", 10000, 100,
			ItemType.PRE_ORDER, LocalDateTime.of(2024, 10, 25, 20, 59), 10, LocalDateTime.now(), 1L, "categoryA");
	}
}
