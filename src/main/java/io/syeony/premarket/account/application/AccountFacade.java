package io.syeony.premarket.account.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.account.application.dto.RegisterAccountDto;
import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.model.VerificationCodeEvent;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.VerificationCodeVerifier;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountFacade {

	private final RegisterAccountProcessor registerAccountProcessor;
	private final IssueVerificationCodeProcessor issueVerificationCodeProcessor;
	private final VerificationCodeVerifier verificationCodeVerifier;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public MemberId register(final RegisterAccountDto accountDto, final String verificationCode) {
		verificationCodeVerifier.verify(accountDto.email(), verificationCode);
		return registerAccountProcessor.register(accountDto.toDomain());
	}

	@Transactional
	public void issueVerification(final String toEmail) {
		VerificationCode verificationCode = issueVerificationCodeProcessor.issue(toEmail);
		publisher.publishEvent(
			VerificationCodeEvent.createEvent(verificationCode.getToEmail(), verificationCode.getCode()));
	}
}
