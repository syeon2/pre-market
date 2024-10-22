package io.syeony.premarket.account.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.account.domain.model.VerificationCode;

public interface VerificationCodeReader {

	Optional<VerificationCode> findByToEmail(String email);
}
