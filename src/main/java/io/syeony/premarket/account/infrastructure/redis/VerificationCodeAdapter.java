package io.syeony.premarket.account.infrastructure.redis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.reader.VerificationCodeReader;
import io.syeony.premarket.account.domain.processor.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VerificationCodeAdapter implements VerificationCodeReader, VerificationCodeRepository {

	private final RedisVerificationCodeRepository redisVerificationCodeRepository;
	private final VerificationCodeMapper verificationCodeMapper;

	@Override
	public void save(VerificationCode verificationCode) {
		redisVerificationCodeRepository.save(verificationCodeMapper.toEntity(verificationCode));
	}

	@Override
	public Optional<VerificationCode> findByToEmail(String toEmail) {
		return redisVerificationCodeRepository.findByToEmail(toEmail)
			.map(verificationCodeMapper::toDomain);
	}
}
