package io.syeony.premarket.member.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.syeony.premarket.member.domain.processor.AuthenticateMemberVerifier;
import io.syeony.premarket.member.domain.processor.IssueVerificationCodeProcessor;
import io.syeony.premarket.member.domain.processor.RefreshTokenVerifier;
import io.syeony.premarket.member.domain.processor.RegisterMemberProcessor;
import io.syeony.premarket.member.domain.processor.VerificationCodeVerifier;
import io.syeony.premarket.member.infrastructure.smpt.MailDispatcher;
import io.syeony.premarket.member.infrastructure.smpt.SmtpMailDispatcher;
import lombok.RequiredArgsConstructor;

@Import({
	RegisterMemberProcessor.class,
	DelegatePasswordEncoder.class,
	VerificationCodeVerifier.class,
	IssueVerificationCodeProcessor.class,
	AuthenticateMemberVerifier.class,
	RefreshTokenVerifier.class
})
@Configuration
@RequiredArgsConstructor
public class MemberModuleConfiguration {

	@Bean
	public MailDispatcher mailDispatcher(JavaMailSender mailSender) {
		return new SmtpMailDispatcher(mailSender);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
