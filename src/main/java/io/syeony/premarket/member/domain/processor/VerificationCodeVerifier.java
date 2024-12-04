package io.syeony.premarket.member.domain.processor;

import io.syeony.premarket.member.domain.processor.reader.VerificationCodeReader;
import io.syeony.premarket.support.error.exception.InvalidVerificationCodeException;
import io.syeony.premarket.support.error.exception.VerificationCodeNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class VerificationCodeVerifier {

	private final VerificationCodeReader verificationCodeReader;

	public void verifyCode(final String email, final String inputCode) {
		var storedCode = verificationCodeReader.findByToEmail(email)
			.orElseThrow(() -> new VerificationCodeNotFoundException(email));

		if (!storedCode.isValid(inputCode)) {
			throw new InvalidVerificationCodeException(inputCode);
		}
	}
}
