package io.syeony.premarket.account.domain.processor;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.domain.processor.repository.VerificationCodeRepository;

class SendVerificationCodeProcessorTest extends UnitTestSupport {

	@InjectMocks
	private SendVerificationCodeProcessor sendVerificationCodeProcessor;

	@Mock
	private VerificationCodeRepository verificationCodeRepository = mock(VerificationCodeRepository.class);

	@BeforeEach
	void setUp() {
		sendVerificationCodeProcessor = new SendVerificationCodeProcessor(verificationCodeRepository);
	}

	@Test
	@DisplayName(value = "Save verification code and return verification code generated this method")
	void save_verificationCode() {
		// given
		String toEmail = "waterkite94@gmail.com";
		VerificationCode verificationCode = VerificationCode.createVerificationCode(toEmail);
		when(verificationCodeRepository.save(any(VerificationCode.class))).thenReturn(verificationCode);

		// when
		VerificationCode result = sendVerificationCodeProcessor.save(toEmail);

		// then
		assertThat(result).isEqualTo(verificationCode);
		verify(verificationCodeRepository, times(1)).save(any(VerificationCode.class));
	}
}
