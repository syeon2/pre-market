package io.syeony.premarket.inquire.presentation;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.inquire.application.InquireFacade;
import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.model.Writer;
import io.syeony.premarket.support.common.AuditTimestamps;

class InquireQueryApiTest extends ControllerTestSupport {

	private InquireFacade inquireFacade = mock(InquireFacade.class);

	@Override
	protected Object initController() {
		return new InquireQueryApi(inquireFacade);
	}

	@Test
	@DisplayName(value = "Find inquires successfully when the item no path parameter and body fields are provided")
	void findItemInquires() throws Exception {
		// given
		Long itemNo = 1L;
		Page<Inquire> mockPage = new PageImpl<>(List.of(createInquireDomain()), PageRequest.of(0, 10), 0);

		given(inquireFacade.findItemInquires(any(), any()))
			.willReturn(mockPage);

		// when // then
		mockMvc.perform(
				get("/api/v1/items/{item_no}/inquires", itemNo)
					.queryParam("page", "0")
					.queryParam("size", "10")
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.content[0].inquire_no").isNumber())
			.andExpect(jsonPath("$.data.content[0].message").isString())
			.andExpect(jsonPath("$.data.content[0].created_at").isString())
			.andExpect(jsonPath("$.data.content[0].updated_at").isString())
			.andExpect(jsonPath("$.data.content[0].writer.member_id").isString())
			.andExpect(jsonPath("$.data.content[0].writer.member_name").isString())
			.andDo(document("inquire-find-item-no",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				pathParameters(
					parameterWithName("item_no").description("상품 고유번호")
				),
				queryParameters(
					parameterWithName("page").description("페이지 번호"),
					parameterWithName("size").description("한 페이지당 컨텐츠 수")
				),
				relaxedResponseFields(
					fieldWithPath("data.content[].inquire_no").type(JsonFieldType.NUMBER).description("문의글 고유번호"),
					fieldWithPath("data.content[].message").type(JsonFieldType.STRING).description("문의글 내용"),
					fieldWithPath("data.content[].created_at").type(JsonFieldType.STRING).description("문의글 생성일"),
					fieldWithPath("data.content[].updated_at").type(JsonFieldType.STRING).description("문의글 수정일"),
					fieldWithPath("data.content[].writer.member_id").type(JsonFieldType.STRING).description("작성자 아이디"),
					fieldWithPath("data.content[].writer.member_name").type(JsonFieldType.STRING).description("작성자 이름")
				)
			));
	}

	private Inquire createInquireDomain() {
		return Inquire.builder()
			.inquireNo(1L)
			.message("message")
			.auditTimestamps(new AuditTimestamps(LocalDateTime.now(), LocalDateTime.now()))
			.writer(Writer.of("memberId", "memberA"))
			.build();
	}
}
