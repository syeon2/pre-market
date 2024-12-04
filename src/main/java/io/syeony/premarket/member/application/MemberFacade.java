package io.syeony.premarket.member.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.member.domain.model.AuthorizationToken;
import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.RefreshTokenEvent;
import io.syeony.premarket.member.domain.model.VerificationCode;
import io.syeony.premarket.member.domain.model.VerificationCodeEvent;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.processor.AuthenticateMemberVerifier;
import io.syeony.premarket.member.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.member.domain.processor.RefreshTokenVerifier;
import io.syeony.premarket.member.domain.processor.RegisterMemberProcessor;
import io.syeony.premarket.member.domain.processor.VerificationCodeVerifier;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberFacade {

	private final RegisterMemberProcessor registerMemberProcessor;
	private final IssueVerificationCodeProcessor issueVerificationCodeProcessor;
	private final AuthenticateMemberVerifier authenticateMemberVerifier;
	private final RefreshTokenVerifier refreshTokenVerifier;
	private final VerificationCodeVerifier verificationCodeVerifier;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public MemberId register(final Member memberDomain, final String verificationCode) {
		verificationCodeVerifier.verifyCode(memberDomain.getEmail(), verificationCode);
		return registerMemberProcessor.register(memberDomain);
	}

	@Transactional
	public void issueVerification(final String toEmail) {
		VerificationCode verificationCode = issueVerificationCodeProcessor.issue(toEmail);
		publisher.publishEvent(
			VerificationCodeEvent.createEvent(verificationCode.getToEmail(), verificationCode.getCode()));
	}

	@Transactional
	public AuthorizationToken authenticateMember(final String email, final String password) {
		AuthorizationToken token = authenticateMemberVerifier.verify(email, password);
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
