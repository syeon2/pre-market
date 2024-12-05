package io.syeony.premarket.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.member.application.MemberAuthenticationFacade;
import io.syeony.premarket.member.application.MemberManageFacade;
import io.syeony.premarket.member.presentation.request.IssueVerificationRequest;
import io.syeony.premarket.member.presentation.request.LoginRequest;
import io.syeony.premarket.member.presentation.request.RegisterMemberRequest;
import io.syeony.premarket.member.presentation.request.RenewTokensRequest;
import io.syeony.premarket.member.presentation.response.LoginResponse;
import io.syeony.premarket.member.presentation.response.RegisterMemberResponse;
import io.syeony.premarket.support.common.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(
	value = "/api",
	produces = MediaType.APPLICATION_JSON_VALUE,
	consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public final class MemberCommandApi {

	private final MemberManageFacade memberManageFacade;
	private final MemberAuthenticationFacade memberAuthenticationFacade;

	@PostMapping("/v1/members")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<RegisterMemberResponse> registerMember(
		@RequestBody @Valid RegisterMemberRequest request
	) {
		var memberId = memberManageFacade.registerMember(request.toDomain(), request.verificationCode());
		return ApiResult.success(new RegisterMemberResponse(memberId.value()));
	}

	@PostMapping("/v1/members/email-verification")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ApiResult<Void> issueVerificationCode(
		@RequestBody @Valid IssueVerificationRequest request
	) {
		memberAuthenticationFacade.issueVerificationCode(request.toEmail());
		return ApiResult.success();
	}

	@PostMapping("/v1/members/login")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<LoginResponse> loginMember(
		@RequestBody @Valid LoginRequest request
	) {
		var token = memberAuthenticationFacade.loginMember(request.email(), request.password());
		return ApiResult.success(new LoginResponse(token.accessToken(), token.refreshToken()));
	}

	@PostMapping("/v1/members/refresh-token")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<LoginResponse> renewToken(
		@RequestBody @Valid RenewTokensRequest request
	) {
		var token = memberAuthenticationFacade.renewToken(request.email(), request.refreshToken());
		return ApiResult.success(new LoginResponse(token.accessToken(), token.refreshToken()));
	}

}
