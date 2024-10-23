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
import io.syeony.premarket.item.domain.model.Seller;
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
	void findRegisteredItemsApi() throws Exception {
		// given
		String memberId = "memberId";
		Page<Item> mockPage = new PageImpl<>(List.of(createItemDomain()), PageRequest.of(0, 10), 0);

		given(itemFacade.findRegisteredItems(any(), any()))
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
			.andExpect(jsonPath("$.data.content[0].category.category_no").isNumber())
			.andExpect(jsonPath("$.data.content[0].category.category_name").isString())
			.andDo(document("item-find-registered",
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
					fieldWithPath("data.content[].category.category_no").type(JsonFieldType.NUMBER)
						.description("카테고리 고유번호"),
					fieldWithPath("data.content[].category.category_name").type(JsonFieldType.STRING)
						.description("카테고리 이름")
				)
			));
	}

	@Test
	@DisplayName(value = "Find items when the category no query parameter is provided")
	void findCategoryItems() throws Exception {
		// given
		Long categoryNo = 1L;
		Page<Item> mockPage = new PageImpl<>(List.of(createItemDomain()), PageRequest.of(0, 10), 0);

		given(itemFacade.retrieveCategoryItems(any(), any()))
			.willReturn(mockPage);

		// when // then
		mockMvc.perform(
				get("/api/v1/items")
					.queryParam("category_no", categoryNo.toString())
					.queryParam("page", "0")
					.queryParam("size", "10")
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.content[0].item_no").isNumber())
			.andExpect(jsonPath("$.data.content[0].item_name").isString())
			.andExpect(jsonPath("$.data.content[0].cost.price").isNumber())
			.andExpect(jsonPath("$.data.content[0].cost.discount").isNumber())
			.andExpect(jsonPath("$.data.content[0].is_pre_order").isBoolean())
			.andExpect(jsonPath("$.data.content[0].stock").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.year").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.month").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.date").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.hour").isNumber())
			.andExpect(jsonPath("$.data.content[0].pre_order_schedule.minute").isNumber())
			.andExpect(jsonPath("$.data.content[0].seller.id").isString())
			.andExpect(jsonPath("$.data.content[0].seller.name").isString())
			.andDo(document("item-find-category-no",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				queryParameters(
					parameterWithName("category_no").description("카테고리 고유번호"),
					parameterWithName("page").description("페이지 번호"),
					parameterWithName("size").description("한 페이지당 컨텐츠 수")
				),
				relaxedResponseFields(
					fieldWithPath("data.content[].item_no").type(JsonFieldType.NUMBER).description("상품 고유번호"),
					fieldWithPath("data.content[].item_name").type(JsonFieldType.STRING).description("상품 이름"),
					fieldWithPath("data.content[].cost.price").type(JsonFieldType.NUMBER).description("상품 정가"),
					fieldWithPath("data.content[].cost.discount").type(JsonFieldType.NUMBER).description("상품 할인금액"),
					fieldWithPath("data.content[].is_pre_order").type(JsonFieldType.BOOLEAN).description("예약 구매 상품 여부"),
					fieldWithPath("data.content[].stock").type(JsonFieldType.NUMBER).description("상품 재고"),
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
					fieldWithPath("data.content[].seller.id").type(JsonFieldType.STRING).description("판매 회원 아이디"),
					fieldWithPath("data.content[].seller.name").type(JsonFieldType.STRING).description("판매 회원 이름")
				)
			));
	}

	@Test
	@DisplayName(value = "Retrieve item detail when the item no path parameter is provided")
	void retrieveItemDetail() throws Exception {
		// given
		Long itemNo = 1L;

		given(itemFacade.retrieveItemDetail(itemNo)).willReturn(createItemDomain());

		// when // then
		mockMvc.perform(
				get("/api/v1/items/{item_no}", itemNo)
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.item_no").isNumber())
			.andExpect(jsonPath("$.data.item_name").isString())
			.andExpect(jsonPath("$.data.cost.price").isNumber())
			.andExpect(jsonPath("$.data.cost.discount").isNumber())
			.andExpect(jsonPath("$.data.is_pre_order").isBoolean())
			.andExpect(jsonPath("$.data.introduction").isString())
			.andExpect(jsonPath("$.data.pre_order_schedule.year").isNumber())
			.andExpect(jsonPath("$.data.pre_order_schedule.month").isNumber())
			.andExpect(jsonPath("$.data.pre_order_schedule.date").isNumber())
			.andExpect(jsonPath("$.data.pre_order_schedule.hour").isNumber())
			.andExpect(jsonPath("$.data.pre_order_schedule.minute").isNumber())
			.andExpect(jsonPath("$.data.created_at").isString())
			.andExpect(jsonPath("$.data.seller.id").isString())
			.andExpect(jsonPath("$.data.seller.name").isString())
			.andDo(document("item-retrieve-detail",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				pathParameters(
					parameterWithName("item_no").description("상품 고유번호")
				),
				relaxedResponseFields(
					fieldWithPath("data.item_no").type(JsonFieldType.NUMBER).description("상품 고유번호"),
					fieldWithPath("data.item_name").type(JsonFieldType.STRING).description("상품 이름"),
					fieldWithPath("data.cost.price").type(JsonFieldType.NUMBER).description("상품 정가"),
					fieldWithPath("data.cost.discount").type(JsonFieldType.NUMBER).description("상품 할인금액"),
					fieldWithPath("data.is_pre_order").type(JsonFieldType.BOOLEAN).description("예약 구매 상품 여부"),
					fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("상품 설명"),
					fieldWithPath("data.pre_order_schedule.year").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 연"),
					fieldWithPath("data.pre_order_schedule.month").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 월"),
					fieldWithPath("data.pre_order_schedule.date").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 일"),
					fieldWithPath("data.pre_order_schedule.hour").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 시간"),
					fieldWithPath("data.pre_order_schedule.minute").type(JsonFieldType.NUMBER)
						.description("예약 주문 가능 분"),
					fieldWithPath("data.created_at").type(JsonFieldType.STRING).description("상품 등록일"),
					fieldWithPath("data.seller.id").type(JsonFieldType.STRING).description("판매 회원 아이디"),
					fieldWithPath("data.seller.name").type(JsonFieldType.STRING).description("판매 회원 이름")
				)
			));
	}

	private Item createItemDomain() {
		return Item.builder()
			.itemNo(1L)
			.itemName("itemA")
			.cost(new Cost(10000, 100))
			.stock(10)
			.itemType(ItemType.PRE_ORDER)
			.introduction("hello introduction")
			.preOrderSchedule(LocalDateTime.of(2024, 10, 24, 23, 58))
			.auditTimestamps(new AuditTimestamps(LocalDateTime.now(), null))
			.category(Category.of(1L, "categoryName"))
			.seller(Seller.of(1L, "memberId", "memberA"))
			.build();
	}
}
