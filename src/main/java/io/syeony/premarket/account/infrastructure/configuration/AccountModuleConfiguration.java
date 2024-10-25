package io.syeony.premarket.account.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.syeony.premarket.account.domain.processor.AuthenticateAccountVerifier;
import io.syeony.premarket.account.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.account.domain.processor.RefreshTokenVerifier;
import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.VerificationCodeVerifier;
import io.syeony.premarket.account.infrastructure.smpt.MailDispatcher;
import io.syeony.premarket.account.infrastructure.smpt.SmtpMailDispatcher;
import lombok.RequiredArgsConstructor;

@Import({
	RegisterAccountProcessor.class,
	DelegatePasswordEncoder.class,
	VerificationCodeVerifier.class,
	IssueVerificationCodeProcessor.class,
	AuthenticateAccountVerifier.class,
	RefreshTokenVerifier.class
})
@Configuration
@RequiredArgsConstructor
public class AccountModuleConfiguration {

	@Bean
	public MailDispatcher mailDispatcher(JavaMailSender mailSender) {
		return new SmtpMailDispatcher(mailSender);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
