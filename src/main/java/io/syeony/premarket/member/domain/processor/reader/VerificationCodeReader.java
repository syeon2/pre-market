package io.syeony.premarket.member.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.member.domain.model.VerificationCode;

public interface VerificationCodeReader {

	Optional<VerificationCode> findByToEmail(String email);
}
