package io.syeony.premarket.item.persentation;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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

	@Test
	@DisplayName(value = "Delete category successfully when the category id path parameter is provided")
	void deleteCategory() throws Exception {
		// given
		long categoryNo = 1L;

		// when // then
		mockMvc.perform(
				delete("/api/v1/categories/{category_no}", categoryNo)
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andDo(document("category-delete",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				pathParameters(
					parameterWithName("category_no").description("카테고리 아이디")
				)
			));
	}
}
