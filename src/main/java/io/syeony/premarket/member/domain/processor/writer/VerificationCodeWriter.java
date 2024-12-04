package io.syeony.premarket.member.domain.processor.writer;

import io.syeony.premarket.member.domain.model.VerificationCode;

public interface VerificationCodeWriter {

	void save(VerificationCode verificationCode);
}
