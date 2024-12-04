package io.syeony.premarket.account.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.AuthorizationToken;
import io.syeony.premarket.account.domain.model.RefreshTokenEvent;
import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.model.VerificationCodeEvent;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.AuthenticateAccountVerifier;
import io.syeony.premarket.account.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.account.domain.processor.RefreshTokenVerifier;
import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.VerificationCodeVerifier;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberFacade {

	private final RegisterAccountProcessor registerAccountProcessor;
	private final IssueVerificationCodeProcessor issueVerificationCodeProcessor;
	private final AuthenticateAccountVerifier authenticateAccountVerifier;
	private final RefreshTokenVerifier refreshTokenVerifier;
	private final VerificationCodeVerifier verificationCodeVerifier;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public MemberId register(final Account accountDomain, final String verificationCode) {
		verificationCodeVerifier.verify(accountDomain.getEmail(), verificationCode);
		return registerAccountProcessor.register(accountDomain);
	}

	@Transactional
	public void issueVerification(final String toEmail) {
		VerificationCode verificationCode = issueVerificationCodeProcessor.issue(toEmail);
		publisher.publishEvent(
			VerificationCodeEvent.createEvent(verificationCode.getToEmail(), verificationCode.getCode()));
	}

	@Transactional
	public AuthorizationToken authenticateAccount(final String email, final String password) {
		AuthorizationToken token = authenticateAccountVerifier.verify(email, password);
		publisher.publishEvent(new RefreshTokenEvent(email, token.refreshToken()));

		return token;
	}

	@Transactional
	public AuthorizationToken authenticateRefreshToken(final String email, final String refreshToken) {
		AuthorizationToken verify = refreshTokenVerifier.verify(email, refreshToken);
		publisher.publishEvent(new RefreshTokenEvent(email, verify.refreshToken()));

		return verify;
	}
}
