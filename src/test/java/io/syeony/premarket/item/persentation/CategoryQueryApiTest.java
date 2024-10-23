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
		given(categoryFacade.retrieveAllCategories())
			.willReturn(List.of(Category.of(1L, "categoryA")));

		// when // then
		mockMvc.perform(
				get("/api/v1/categories")
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("category-retrieve-all",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				relaxedResponseFields(
					fieldWithPath("data.categories[].category_no").type(JsonFieldType.NUMBER).description("카테고리 고유번호"),
					fieldWithPath("data.categories[].category_name").type(JsonFieldType.STRING).description("카테고리 이름")
				)
			));
	}
}
