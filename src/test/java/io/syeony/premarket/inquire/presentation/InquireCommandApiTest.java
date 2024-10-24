package io.syeony.premarket.inquire.presentation;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.inquire.application.InquireFacade;
import io.syeony.premarket.inquire.presentation.request.CreateInquireRequest;
import io.syeony.premarket.inquire.presentation.request.DeleteInquireRequest;

class InquireCommandApiTest extends ControllerTestSupport {

	@Mock
	private InquireFacade inquireFacade = mock(InquireFacade.class);

	@Override
	protected Object initController() {
		return new InquireCommandApi(inquireFacade);
	}

	@Test
	@DisplayName(value = "Create inquire successfully when them request fields are provided")
	void createInquire() throws Exception {
		// given
		CreateInquireRequest request = new CreateInquireRequest("message", 1L, "memberId");

		// when // then
		mockMvc.perform(
				post("/api/v1/items/inquires")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isCreated())
			.andDo(document("inquire-create",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("message").type(JsonFieldType.STRING).description("문의 글"),
					fieldWithPath("item_no").type(JsonFieldType.NUMBER).description("상품 번호"),
					fieldWithPath("member_id").type(JsonFieldType.STRING).description("작성자 아이디")
				)
			));
	}

	@Test
	void deleteInquire() throws Exception {
		// given
		Long inquireNo = 1L;
		DeleteInquireRequest request = new DeleteInquireRequest("memberId", 1L);

		// when // then
		mockMvc.perform(
				delete("/api/v1/items/inquires/{inquire_no}", inquireNo)
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("inquire-delete",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("item_no").type(JsonFieldType.NUMBER).description("상품 번호"),
					fieldWithPath("member_id").type(JsonFieldType.STRING).description("작성자 아이디")
				)
			));
	}
}
