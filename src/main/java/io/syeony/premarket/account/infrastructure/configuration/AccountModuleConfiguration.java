package io.syeony.premarket.account.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;

import io.syeony.premarket.account.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.account.domain.processor.RegisterAccountProcessor;
import io.syeony.premarket.account.domain.processor.VerificationCodeVerifier;
import io.syeony.premarket.account.infrastructure.smpt.MailDispatcher;
import io.syeony.premarket.account.infrastructure.smpt.SmtpMailDispatcher;

@Import({
	RegisterAccountProcessor.class,
	DelegatePasswordEncoder.class,
	VerificationCodeVerifier.class,
	IssueVerificationCodeProcessor.class
})
@Configuration
public class AccountModuleConfiguration {

	@Bean
	public MailDispatcher mailDispatcher(JavaMailSender mailSender) {
		return new SmtpMailDispatcher(mailSender);
	}
}
