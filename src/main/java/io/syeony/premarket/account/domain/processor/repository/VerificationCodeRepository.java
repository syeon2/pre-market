package io.syeony.premarket.account.domain.processor.repository;

import io.syeony.premarket.account.domain.model.VerificationCode;

public interface VerificationCodeRepository {

	void save(VerificationCode verificationCode);
}
