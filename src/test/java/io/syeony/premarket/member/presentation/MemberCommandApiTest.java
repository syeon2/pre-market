package io.syeony.premarket.member.presentation;

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
import io.syeony.premarket.member.application.MemberAuthenticationFacade;
import io.syeony.premarket.member.application.MemberManageFacade;
import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.presentation.request.IssueVerificationRequest;
import io.syeony.premarket.member.presentation.request.LoginRequest;
import io.syeony.premarket.member.presentation.request.RegisterMemberRequest;
import io.syeony.premarket.member.presentation.request.RenewTokensRequest;
import io.syeony.premarket.member.presentation.request.vo.AddressRequest;

class MemberCommandApiTest extends ControllerTestSupport {

	private final MemberManageFacade memberManageFacade = mock(MemberManageFacade.class);
	private final MemberAuthenticationFacade memberAuthenticationFacade = mock(MemberAuthenticationFacade.class);

	@Override
	protected Object initController() {
		return new MemberCommandApi(memberManageFacade, memberAuthenticationFacade);
	}

	@Test
	@DisplayName(value = "Register member successfully when all required fields are provided")
	void registerCustomerMemberApi() throws Exception {
		// given
		RegisterMemberRequest request = createRegisterCustomerMemberRequest();

		given(memberManageFacade.registerMember(request.toDomain(), request.verificationCode()))
			.willReturn(new MemberId("memberId"));

		// when // then
		mockMvc.perform(
				post("/api/v1/members")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.member_id").isString())
			.andDo(document("member-register",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
					fieldWithPath("phone_number").type(JsonFieldType.STRING).description("전화번호"),
					fieldWithPath("address.base_address").type(JsonFieldType.STRING).description("주소1"),
					fieldWithPath("address.address_detail").type(JsonFieldType.STRING).description("주소2"),
					fieldWithPath("address.zipcode").type(JsonFieldType.STRING).description("우편 번호"),
					fieldWithPath("verification_code").type(JsonFieldType.STRING).description("가입 인증 번호")
				),
				relaxedResponseFields(
					fieldWithPath("data.member_id").type(JsonFieldType.STRING).description("가입된 회원 고유 아이디")
				)
			));
	}

	@Test
	@DisplayName(value = "Issue verification code when toEmail field is provided")
	void issueVerificationCodeApi() throws Exception {
		// given
		IssueVerificationRequest request = new IssueVerificationRequest("waterkite94@gmail.com");

		// when // then
		mockMvc.perform(
				post("/api/v1/members/email-verification")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isAccepted())
			.andDo(document("member-email-verification",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("to_email").type(JsonFieldType.STRING).description("전송할 이메일")
				)
			));
	}

	@Test
	@DisplayName(value = "Login member when email and password field are provided")
	void loginApi() throws Exception {
		// given
		LoginRequest request = new LoginRequest("waterkite94@gmail.com", "rawPassword");

		given(memberAuthenticationFacade.authenticateMember(request.email(), request.password()))
			.willReturn(new AuthorizationToken("access_token_value", "refresh_token_value"));

		// when // then
		mockMvc.perform(
				post("/api/v1/members/login")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("member-login",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
				),
				relaxedResponseFields(
					fieldWithPath("data.access_token").type(JsonFieldType.STRING).description("Access Token"),
					fieldWithPath("data.refresh_token").type(JsonFieldType.STRING).description("Refresh Token")
				)
			));
	}

	@Test
	@DisplayName(value = "Renew tokens when email and refresh token field are provided")
	void renewTokenApi() throws Exception {
		// given
		RenewTokensRequest request =
			new RenewTokensRequest("waterkite94@gmail.com", "refresh_token_value");

		given(memberAuthenticationFacade.renewToken(request.email(), request.refreshToken()))
			.willReturn(new AuthorizationToken("access_token_value", "refresh_token_value"));

		// when // then
		mockMvc.perform(
				post("/api/v1/members/refresh-token")
					.content(objectMapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
			).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("member-renew-token",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				requestFields(
					fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
					fieldWithPath("refresh_token").type(JsonFieldType.STRING).description("비밀번호")
				),
				relaxedResponseFields(
					fieldWithPath("data.access_token").type(JsonFieldType.STRING).description("Access Token"),
					fieldWithPath("data.refresh_token").type(JsonFieldType.STRING).description("Refresh Token")
				)
			));
	}

	private RegisterMemberRequest createRegisterCustomerMemberRequest() {
		return new RegisterMemberRequest(
			"waterkite94@gmail.com",
			"rawPassword",
			"suyeon",
			"00011112222",
			new AddressRequest("address1", "address2", "zipcode"),
			"000111"
		);
	}
}
