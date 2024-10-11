package io.syeony.premarket.account.domain.processor.reader;

import io.syeony.premarket.account.domain.model.VerificationCode;

public interface VerificationCodeReader {

	VerificationCode findByEmail(String email);
}
