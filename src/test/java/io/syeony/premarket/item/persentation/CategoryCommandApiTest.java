package io.syeony.premarket.item.persentation;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.item.application.CategoryFacade;
import io.syeony.premarket.item.persentation.request.CreateCategoryRequest;

class CategoryCommandApiTest extends ControllerTestSupport {

	@Mock
	private final CategoryFacade categoryFacade = mock(CategoryFacade.class);

	@Override
	protected Object initController() {
		return new CategoryCommandApi(categoryFacade);
	}

	@Test
	@DisplayName(value = "Create category successfully when the name field is provided")
	void createCategory() throws Exception {
		// given
		CreateCategoryRequest request = new CreateCategoryRequest("categoryA");

		// when // then
		mockMvc.perform(
				post("/api/v1/categories")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andDo(document("category-create",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("name").type(JsonFieldType.STRING).description("카테고리 이름")
				)
			));
	}
}
