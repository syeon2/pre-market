package io.syeony.premarket.account.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.account.application.MemberFacade;
import io.syeony.premarket.account.presentation.request.IssueVerificationRequest;
import io.syeony.premarket.account.presentation.request.LoginRequest;
import io.syeony.premarket.account.presentation.request.RegisterAccountRequest;
import io.syeony.premarket.account.presentation.request.RenewTokensRequest;
import io.syeony.premarket.account.presentation.response.AuthorizeTokenResponse;
import io.syeony.premarket.account.presentation.response.RegisterAccountResponse;
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

	private final MemberFacade memberFacade;

	@PostMapping("/v1/members")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<RegisterAccountResponse> registerMember(
		@RequestBody @Valid RegisterAccountRequest request
	) {
		var memberId = memberFacade.register(request.toDomain(), request.verificationCode());
		return ApiResult.success(new RegisterAccountResponse(memberId.value()));
	}

	@PostMapping("/v1/members/email-verification")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ApiResult<Void> issueVerificationCode(
		@RequestBody @Valid IssueVerificationRequest request
	) {
		memberFacade.issueVerification(request.toEmail());
		return ApiResult.success();
	}

	@PostMapping("/v1/members/login")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<AuthorizeTokenResponse> loginAccount(
		@RequestBody @Valid LoginRequest request
	) {
		var token = memberFacade.authenticateAccount(request.email(), request.password());
		return ApiResult.success(new AuthorizeTokenResponse(token.accessToken(), token.refreshToken()));
	}

	@PostMapping("/v1/members/refresh-token")
	@ResponseStatus(HttpStatus.OK)
	public ApiResult<AuthorizeTokenResponse> renewToken(
		@RequestBody @Valid RenewTokensRequest request
	) {
		var token = memberFacade.authenticateRefreshToken(request.email(), request.refreshToken());
		return ApiResult.success(new AuthorizeTokenResponse(token.accessToken(), token.refreshToken()));
	}

}
