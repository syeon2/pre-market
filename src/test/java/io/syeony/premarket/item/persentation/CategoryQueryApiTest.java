package io.syeony.premarket.item.persentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.item.application.CategoryFacade;
import io.syeony.premarket.item.domain.model.Category;

class CategoryQueryApiTest extends ControllerTestSupport {

	private final CategoryFacade categoryFacade = mock(CategoryFacade.class);

	@Override
	protected Object initController() {
		return new CategoryQueryApi(categoryFacade);
	}

	@Test
	@DisplayName(value = "Retrieve all categories successfully")
	void retrieveAllCategories() throws Exception {
		// given
		List<Category> categories = createCategoryList();
		given(categoryFacade.retrieveAllCategories()).willReturn(categories);

		// when // then
		mockMvc.perform(
				get("/api/v1/categories")
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("category-retrieve-all",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				responseFields(
					fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태 코드"),
					fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
					fieldWithPath("data.categories[].id").type(JsonFieldType.NUMBER).description("카테고리 고유번호"),
					fieldWithPath("data.categories[].name").type(JsonFieldType.STRING).description("카테고리 이름")
				)
			));
	}

	private List<Category> createCategoryList() {
		return List.of(Category.of(1L, "categoryA", null));
	}
}
