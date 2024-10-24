package io.syeony.premarket.inquire.presentation;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
		String itemId = "itemId";
		CreateInquireRequest request = new CreateInquireRequest("comment", "memberId");

		// when // then
		mockMvc.perform(
				post("/api/v1/items/{item_id}/inquires", itemId)
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isCreated())
			.andDo(document("inquire-create",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("comment").type(JsonFieldType.STRING).description("문의 글"),
					fieldWithPath("member_id").type(JsonFieldType.STRING).description("작성자 아이디")
				)
			));
	}
}
