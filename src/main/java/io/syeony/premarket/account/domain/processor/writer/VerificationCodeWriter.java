package io.syeony.premarket.account.domain.processor.writer;

import io.syeony.premarket.account.domain.model.VerificationCode;

public interface VerificationCodeWriter {

	void save(VerificationCode verificationCode);
}
