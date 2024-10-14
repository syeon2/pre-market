package io.syeony.premarket.account.infrastructure.redis;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.RedisInfraTestSupport;
import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.infrastructure.redis.entity.VerificationCodeEntity;

@Import({VerificationCodeAdapter.class})
class VerificationCodeAdapterTest extends RedisInfraTestSupport {

	@Autowired
	private VerificationCodeAdapter verificationCodeAdapter;

	@Autowired
	private VerificationCodeRepository verificationCodeRepository;

	@Test
	@DisplayName(value = "Find verification code by email")
	void findByEmail_shouldReturnVerificationCode() {
		// given
		String email = "waterkite94@gmail.com";
		String verificationCode = "verificationCode";

		verificationCodeRepository.save(new VerificationCodeEntity(email, verificationCode));

		// when
		VerificationCode findVerificationCode = verificationCodeAdapter.findByEmail(email);

		// then
		assertThat(findVerificationCode.value()).isEqualTo(verificationCode);
	}

	@Test
	@DisplayName(value = "Cant find verification code by wrong email")
	void findByEmail_shouldReturnNull() {
		// given
		String email = "waterkite94@gmail.com";
		String verificationCode = "verificationCode";

		verificationCodeRepository.save(new VerificationCodeEntity(email, verificationCode));

		// when
		VerificationCode findVerificationCode = verificationCodeAdapter.findByEmail("another@gmail.com");

		// then
		assertThat(findVerificationCode.value()).isNull();
	}
}
