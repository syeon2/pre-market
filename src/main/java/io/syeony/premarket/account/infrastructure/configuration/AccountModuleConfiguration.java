package io.syeony.premarket.account.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.VerificationCodeVerifier;

@Import({
	RegisterAccountProcessor.class,
	DelegatePasswordEncoder.class,
	VerificationCodeVerifier.class,
})
@Configuration
public class AccountModuleConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
