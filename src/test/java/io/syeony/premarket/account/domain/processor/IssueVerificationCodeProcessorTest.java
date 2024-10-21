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

class IssueVerificationCodeProcessorTest extends UnitTestSupport {

	@InjectMocks
	private IssueVerificationCodeProcessor issueVerificationCodeProcessor;

	@Mock
	private VerificationCodeRepository verificationCodeRepository = mock(VerificationCodeRepository.class);

	@BeforeEach
	void setUp() {
		issueVerificationCodeProcessor = new IssueVerificationCodeProcessor(verificationCodeRepository);
	}

	@Test
	@DisplayName(value = "Save verification code and return verification code generated this method")
	void issue_verificationCode() {
		// given
		String toEmail = "waterkite94@gmail.com";
		VerificationCode verificationCode = VerificationCode.issueVerificationCode(toEmail);

		// when
		issueVerificationCodeProcessor.issue(toEmail);

		// then
		assertThat(verificationCode.getToEmail()).isEqualTo(toEmail);
		assertThat(verificationCode.getCode()).isNotNull();
		verify(verificationCodeRepository, times(1)).save(any(VerificationCode.class));
	}
}