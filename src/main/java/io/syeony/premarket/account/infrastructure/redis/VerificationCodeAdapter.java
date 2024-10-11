package io.syeony.premarket.account.infrastructure.redis;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.reader.VerificationCodeReader;

@Repository
public class VerificationCodeAdapter implements VerificationCodeReader {

	@Override
	public VerificationCode findByEmail(String email) {
		return new VerificationCode(null);
	}
}
