package io.syeony.premarket.account.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.syeony.premarket.account.application.AccountFacade;
import io.syeony.premarket.account.presentation.request.RegisterAccountRequest;
import io.syeony.premarket.account.presentation.request.SendVerificationEmailRequest;
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
		var memberId = accountFacade.register(request.toRegisterAccountDto(), request.verificationCode());
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(ApiResult.created(new RegisterAccountResponse(memberId.value())));
	}

	@PostMapping("/v1/accounts/email-verification")
	public ResponseEntity<Void> sendVerificationEmail(
		@RequestBody @Valid SendVerificationEmailRequest request
	) {
		accountFacade.sendVerificationCode(request.toEmail());

		return ResponseEntity
			.status(HttpStatus.ACCEPTED)
			.build();
	}
}
