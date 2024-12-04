package io.syeony.premarket.member.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.model.RefreshTokenEvent;
import io.syeony.premarket.member.domain.model.VerificationCode;
import io.syeony.premarket.member.domain.model.VerificationCodeEvent;
import io.syeony.premarket.member.domain.processor.AuthenticateMemberVerifier;
import io.syeony.premarket.member.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.member.domain.processor.RefreshTokenVerifier;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberAuthenticationFacade {

	private final IssueVerificationCodeProcessor issueVerificationCodeProcessor;
	private final AuthenticateMemberVerifier authenticateMemberVerifier;
	private final RefreshTokenVerifier refreshTokenVerifier;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public AuthorizationToken authenticateMember(final String email, final String password) {
		AuthorizationToken token = authenticateMemberVerifier.verify(email, password);
		publisher.publishEvent(new RefreshTokenEvent(email, token.refreshToken()));

		return token;
	}

	@Transactional
	public AuthorizationToken renewToken(final String email, final String refreshToken) {
		AuthorizationToken verify = refreshTokenVerifier.verify(email, refreshToken);
		publisher.publishEvent(new RefreshTokenEvent(email, verify.refreshToken()));

		return verify;
	}

	@Transactional
	public void issueVerificationCode(final String toEmail) {
		VerificationCode verificationCode = issueVerificationCodeProcessor.process(toEmail);
		publisher.publishEvent(
			VerificationCodeEvent.createEvent(verificationCode.getToEmail(), verificationCode.getCode()));
	}
}
