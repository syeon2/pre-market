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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.item.application.ItemFacade;
import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;
import io.syeony.premarket.support.common.AuditTimestamps;

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
		Page<Item> mockPage = new PageImpl<>(List.of(createItemDomain()), PageRequest.of(0, 10), 0);

		given(itemFacade.retrieveRegisteredItems(any(), any()))
			.willReturn(mockPage);

		// when // then
		mockMvc.perform(
				get("/api/v1/members/{memberId}/registered_items", memberId)
					.queryParam("page", "0")
					.queryParam("size", "10")
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.content[0].item_no").isNumber())
			.andExpect(jsonPath("$.data.content[0].item_name").isString())
			.andExpect(jsonPath("$.data.content[0].cost.price").isNumber())
			.andExpect(jsonPath("$.data.content[0].cost.discount").isNumber())
			.andExpect(jsonPath("$.data.content[0].stock").isNumber())
			.andExpect(jsonPath("$.data.content[0].item_type").isString())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.year").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.month").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.date").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.hour").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.minute").isNumber())
			.andExpect(jsonPath("$.data.content[0].created_at").isString())
			.andExpect(jsonPath("$.data.content[0].category.id").isNumber())
			.andExpect(jsonPath("$.data.content[0].category.name").isString())
			.andDo(document("item-retrieve-registered",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				pathParameters(
					parameterWithName("memberId").description("회원 아이디")
				),
				queryParameters(
					parameterWithName("page").description("페이지 번호"),
					parameterWithName("size").description("한 페이지당 컨텐츠 수")
				),
				relaxedResponseFields(
					fieldWithPath("data.content[].item_no").type(JsonFieldType.NUMBER).description("상품 고유번호"),
					fieldWithPath("data.content[].item_name").type(JsonFieldType.STRING).description("상품 이름"),
					fieldWithPath("data.content[].cost.price").type(JsonFieldType.NUMBER).description("상품 정가"),
					fieldWithPath("data.content[].cost.discount").type(JsonFieldType.NUMBER).description("상품 할인금액"),
					fieldWithPath("data.content[].stock").type(JsonFieldType.NUMBER).description("상품 재고"),
					fieldWithPath("data.content[].item_type").type(JsonFieldType.STRING).description("상품 타입"),
					fieldWithPath("data.content[].pre_order_schedule.year").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 연"),
					fieldWithPath("data.content[].pre_order_schedule.month").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 월"),
					fieldWithPath("data.content[].pre_order_schedule.date").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 일"),
					fieldWithPath("data.content[].pre_order_schedule.hour").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 시간"),
					fieldWithPath("data.content[].pre_order_schedule.minute").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 분"),
					fieldWithPath("data.content[].created_at").type(JsonFieldType.STRING).description("상품 등록일"),
					fieldWithPath("data.content[].category.id").type(JsonFieldType.NUMBER).description("카테고리 고유번호"),
					fieldWithPath("data.content[].category.name").type(JsonFieldType.STRING).description("카테고리 이름")
				)
			));
	}

	private Item createItemDomain() {
		return Item.builder()
			.id(1L)
			.name("itemA")
			.cost(new Cost(10000, 100))
			.stock(10)
			.itemType(ItemType.PRE_ORDER)
			.preOrderSchedule(LocalDateTime.of(2024, 10, 24, 23, 58))
			.auditTimestamps(new AuditTimestamps(LocalDateTime.now(), null))
			.category(Category.builder().id(1L).name("categoryName").build())
			.build();
	}
}
