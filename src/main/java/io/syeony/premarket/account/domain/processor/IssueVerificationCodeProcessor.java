package io.syeony.premarket.account.domain.processor;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.writer.VerificationCodeWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IssueVerificationCodeProcessor {

	private final VerificationCodeWriter verificationCodeWriter;

	public VerificationCode issue(final String toEmail) {
		VerificationCode verificationCode = VerificationCode.issueVerificationCode(toEmail);
		verificationCodeWriter.save(verificationCode);

		return verificationCode;
	}
}
