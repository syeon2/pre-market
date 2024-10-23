package io.syeony.premarket.account.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.account.application.AccountFacade;
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
public final class AccountCommandApi {

	private final AccountFacade accountFacade;

	@PostMapping("/v1/accounts")
	public ResponseEntity<ApiResult<RegisterAccountResponse>> register(
		@RequestBody @Valid RegisterAccountRequest request
	) {
		var memberId = accountFacade.register(request.toDomain(), request.verificationCode());
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResult.created(new RegisterAccountResponse(memberId.value())));
	}

	@PostMapping("/v1/accounts/email-verification")
	public ResponseEntity<Void> issueVerificationCode(
		@RequestBody @Valid IssueVerificationRequest request
	) {
		accountFacade.issueVerification(request.toEmail());
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@PostMapping("/v1/accounts/login")
	public ResponseEntity<ApiResult<AuthorizeTokenResponse>> loginAccount(
		@RequestBody @Valid LoginRequest request
	) {
		var token = accountFacade.authenticateAccount(request.email(), request.password());
		return ResponseEntity.ok()
			.body(ApiResult.ok(new AuthorizeTokenResponse(token.accessToken(), token.refreshToken())));
	}

	@PostMapping("/v1/accounts/refresh-token")
	public ResponseEntity<ApiResult<AuthorizeTokenResponse>> renewToken(
		@RequestBody @Valid RenewTokensRequest request
	) {
		var token = accountFacade.authenticateRefreshToken(request.email(), request.refreshToken());
		return ResponseEntity.ok()
			.body(ApiResult.ok(new AuthorizeTokenResponse(token.accessToken(), token.refreshToken())));
	}

}
