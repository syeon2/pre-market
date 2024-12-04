package io.syeony.premarket.member.domain.processor;

import io.syeony.premarket.member.domain.model.VerificationCode;
import io.syeony.premarket.member.domain.processor.writer.VerificationCodeWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IssueVerificationCodeProcessor {

	private final VerificationCodeWriter verificationCodeWriter;

	public VerificationCode process(final String toEmail) {
		VerificationCode verificationCode = VerificationCode.issueVerificationCode(toEmail);
		verificationCodeWriter.save(verificationCode);

		return verificationCode;
	}
}
