package io.syeony.premarket.account.domain.processor;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendVerificationCodeProcessor {

	private final VerificationCodeRepository verificationCodeRepository;

	public VerificationCode save(final String toEmail) {
		return verificationCodeRepository.save(
			VerificationCode.createVerificationCode(toEmail));
	}
}
