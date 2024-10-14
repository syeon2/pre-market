package io.syeony.premarket.account.domain.processor;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IssueVerificationCodeProcessor {

	private final VerificationCodeRepository verificationCodeRepository;

	public VerificationCode issue(final String toEmail) {
		VerificationCode verificationCode = VerificationCode.issueVerificationCode(toEmail);
		verificationCodeRepository.save(verificationCode);

		return verificationCode;
	}
}
