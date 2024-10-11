package io.syeony.premarket.account.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.account.application.dto.RegisterAccountDto;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.reader.VerificationCodeReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountFacade {

	private final RegisterAccountProcessor registerAccountProcessor;
	private final VerificationCodeReader verificationCodeReader;

	@Transactional
	public MemberId register(RegisterAccountDto accountDto, String verificationCode) {
		validateVerificationCodeByEmail(accountDto.email(), verificationCode);
		return registerAccountProcessor.register(accountDto.toDomain());
	}

	private void validateVerificationCodeByEmail(String email, String verificationCode) {
		if (!verificationCodeReader.findByEmail(email).isValid(verificationCode)) {
			throw new IllegalArgumentException("Invalid verification code");
		}
	}
}
