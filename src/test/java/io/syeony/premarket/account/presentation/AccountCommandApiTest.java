package io.syeony.premarket.account.presentation;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import io.syeony.premarket.ControllerTestSupport;
import io.syeony.premarket.account.application.AccountFacade;
import io.syeony.premarket.account.presentation.request.RegisterAccountRequest;

class AccountCommandApiTest extends ControllerTestSupport {

	private final AccountFacade accountFacade = mock(AccountFacade.class);

	@Override
	protected Object initController() {
		return new AccountCommandApi(accountFacade);
	}

	@Test
	@DisplayName(value = "Register account successfully when all required fields are provided")
	void registerApi() throws Exception {
		// given
		RegisterAccountRequest request = createRegisterAccountRequest();

		given(accountFacade.register(request.toRegisterAccountDto(), request.verificationCode()))
			.willReturn("memberId");

		// when // then
		mockMvc.perform(
				post("/api/v1/accounts")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.member_id").isString())
			.andDo(document("account-register",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
					fieldWithPath("phone_number").type(JsonFieldType.STRING).description("전화번호"),
					fieldWithPath("address.address1").type(JsonFieldType.STRING).description("주소1"),
					fieldWithPath("address.address2").type(JsonFieldType.STRING).description("주소2"),
					fieldWithPath("address.zipcode").type(JsonFieldType.STRING).description("우편 번호"),
					fieldWithPath("verification_code").type(JsonFieldType.STRING).description("가입 인증 번호")
				),
				responseFields(
					fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태 코드"),
					fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
					fieldWithPath("data.member_id").type(JsonFieldType.STRING).description("가입된 회원 고유 아이디")
				)
			));
	}

	private RegisterAccountRequest createRegisterAccountRequest() {
		return new RegisterAccountRequest(
			"waterkite94@gmail.com",
			"rawPassword",
			"suyeon",
			"00011112222",
			new RegisterAccountRequest.Address("address1", "address2", "zipcode"),
			"000111"
		);
	}
}