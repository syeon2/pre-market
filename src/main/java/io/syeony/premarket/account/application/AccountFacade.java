package io.syeony.premarket.account.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.account.application.dto.RegisterAccountDto;
import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.SendVerificationCodeProcessor;
import io.syeony.premarket.account.domain.processor.VerificationCodeVerifier;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountFacade {

	private final RegisterAccountProcessor registerAccountProcessor;
	private final SendVerificationCodeProcessor sendVerificationCodeProcessor;
	private final VerificationCodeVerifier verificationCodeVerifier;

	@Transactional
	public MemberId register(final RegisterAccountDto accountDto, final String verificationCode) {
		verificationCodeVerifier.verify(accountDto.email(), verificationCode);
		return registerAccountProcessor.register(accountDto.toDomain());
	}

	public void sendVerificationCode(final String toEmail) {
		VerificationCode verificationCode = sendVerificationCodeProcessor.save(toEmail);
	}
}
