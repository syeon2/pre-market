package io.syeony.premarket.member.infrastructure.redis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.member.domain.model.VerificationCode;
import io.syeony.premarket.member.domain.processor.reader.VerificationCodeReader;
import io.syeony.premarket.member.domain.processor.writer.VerificationCodeWriter;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VerificationCodeAdapter implements VerificationCodeReader, VerificationCodeWriter {

	private final VerificationCodeRepository verificationCodeRepository;
	private final VerificationCodeMapper verificationCodeMapper;

	@Override
	public void save(VerificationCode verificationCode) {
		verificationCodeRepository.save(verificationCodeMapper.toEntity(verificationCode));
	}

	@Override
	public Optional<VerificationCode> findByToEmail(String toEmail) {
		return verificationCodeRepository.findByToEmail(toEmail)
			.map(verificationCodeMapper::toDomain);
	}
}
