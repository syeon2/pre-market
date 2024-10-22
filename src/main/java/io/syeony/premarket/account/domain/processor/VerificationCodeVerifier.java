package io.syeony.premarket.account.domain.processor;

import io.syeony.premarket.account.domain.processor.reader.VerificationCodeReader;
import io.syeony.premarket.support.error.exception.InvalidVerificationCodeException;
import io.syeony.premarket.support.error.exception.VerificationCodeNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class VerificationCodeVerifier {

	private final VerificationCodeReader verificationCodeReader;

	public void verify(final String email, final String verificationCode) {
		var findCode = verificationCodeReader.findByToEmail(email)
			.orElseThrow(() -> new VerificationCodeNotFoundException(email));

		if (!findCode.isValid(verificationCode)) {
			throw new InvalidVerificationCodeException(verificationCode);
		}
	}
}
