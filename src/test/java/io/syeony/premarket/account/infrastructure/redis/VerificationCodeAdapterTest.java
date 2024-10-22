package io.syeony.premarket.account.infrastructure.redis;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.RedisInfraTestSupport;
import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.infrastructure.redis.entity.VerificationCodeEntity;

@Import({VerificationCodeAdapter.class, VerificationCodeMapper.class})
class VerificationCodeAdapterTest extends RedisInfraTestSupport {

	@Autowired
	private VerificationCodeAdapter verificationCodeAdapter;

	@Autowired
	private RedisVerificationCodeRepository redisVerificationCodeRepository;

	@Test
	@DisplayName(value = "Save verification code")
	void saveVerificationCode() {
		// given
		String email = "waterkite94@gmail.com";
		VerificationCode verificationCode = VerificationCode.issueVerificationCode(email);

		// when
		verificationCodeAdapter.save(verificationCode);

		// then
		Optional<VerificationCodeEntity> findCodeOptional = redisVerificationCodeRepository.findByToEmail(email);
		assertThat(findCodeOptional.isPresent()).isTrue();
		assertThat(findCodeOptional.get().getCode()).isEqualTo(verificationCode.getCode());
	}

	@Test
	@DisplayName(value = "Find verification code by email")
	void findByToEmail_shouldReturnVerificationCode() {
		// given
		String toEmail = "waterkite94@gmail.com";
		String verificationCode = "verificationCode";

		redisVerificationCodeRepository.save(new VerificationCodeEntity(toEmail, verificationCode));

		// when
		Optional<VerificationCode> findCodeOptional = verificationCodeAdapter.findByToEmail(toEmail);

		// then
		assertThat(findCodeOptional).isPresent();
		assertThat(findCodeOptional.get().getCode()).isEqualTo(verificationCode);
	}

	@Test
	@DisplayName(value = "Cant find verification code by wrong email")
	void findByToEmail_shouldReturnNull() {
		// given
		String toEmail = "waterkite94@gmail.com";
		String verificationCode = "verificationCode";

		redisVerificationCodeRepository.save(new VerificationCodeEntity(toEmail, verificationCode));

		// when
		Optional<VerificationCode> findCodeOptional = verificationCodeAdapter.findByToEmail("another@gmail.com");

		// then
		assertThat(findCodeOptional).isNotPresent();
	}
}
