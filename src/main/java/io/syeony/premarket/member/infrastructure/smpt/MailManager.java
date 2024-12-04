package io.syeony.premarket.member.infrastructure.smpt;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import io.syeony.premarket.member.domain.model.VerificationCodeEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailManager {

	private final VerificationCodeMailDispatcher verificationCodeMailDispatcher;

	private static final String ISSUE_VERIFICATION_CODE_SUBJECT = "회원 가입을 위한 인증번호입니다.";

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handle(VerificationCodeEvent event) {
		verificationCodeMailDispatcher.dispatch(
			new MailMessage(event.toEmail(), ISSUE_VERIFICATION_CODE_SUBJECT, event.code()));
	}
}
