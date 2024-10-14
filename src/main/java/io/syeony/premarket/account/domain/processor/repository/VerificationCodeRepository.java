package io.syeony.premarket.account.domain.processor.repository;

import io.syeony.premarket.account.domain.model.VerificationCode;

public interface VerificationCodeRepository {

	VerificationCode save(VerificationCode verificationCode);
}
