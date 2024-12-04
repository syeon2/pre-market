package io.syeony.premarket.member.domain.processor;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.member.domain.model.VerificationCode;
import io.syeony.premarket.member.domain.processor.reader.VerificationCodeReader;

class VerificationCodeVerifierTest extends UnitTestSupport {

	@InjectMocks
	private VerificationCodeVerifier verificationCodeVerifier;

	@Mock
	private VerificationCodeReader verificationCodeReader = mock(VerificationCodeReader.class);

	@BeforeEach
	void setUp() {
		verificationCodeVerifier = new VerificationCodeVerifier(verificationCodeReader);
	}

	@Test
	@DisplayName(value = "Verify stored verification code and input code from external api")
	void verifyCode() {
		// given
		final String email = "waterkite94@gmail.com";
		final String inputCode = "000111";

		given(verificationCodeReader.findByToEmail(email))
			.willReturn(Optional.of(VerificationCode.of(email, inputCode)));

		// when
		verificationCodeVerifier.verifyCode(email, inputCode);

		// then
		verify(verificationCodeReader, times(1)).findByToEmail(email);
	}
}
