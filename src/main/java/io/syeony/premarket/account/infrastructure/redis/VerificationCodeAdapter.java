package io.syeony.premarket.account.infrastructure.redis;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.reader.VerificationCodeReader;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VerificationCodeAdapter implements VerificationCodeReader {

	private final VerificationCodeRepository verificationCodeRepository;

	@Override
	public VerificationCode findByEmail(String email) {
		return verificationCodeRepository.findByEmail(email)
			.map(verificationCodeEntity -> new VerificationCode(verificationCodeEntity.getCode()))
			.orElseGet(() -> new VerificationCode(null));
	}
}
