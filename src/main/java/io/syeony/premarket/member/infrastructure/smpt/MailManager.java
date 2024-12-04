package io.syeony.premarket.member.infrastructure.smpt;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import io.syeony.premarket.member.domain.model.VerificationCodeEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailManager {

	private final MailDispatcher mailDispatcher;

	private static final String SUBJECT = "회원 가입을 위한 인증번호입니다.";

	@Async
	@TransactionalEventListener
	public void handle(VerificationCodeEvent event) {
		mailDispatcher.dispatch(new MailMessage(event.toEmail(), SUBJECT, event.code()));
	}
}
