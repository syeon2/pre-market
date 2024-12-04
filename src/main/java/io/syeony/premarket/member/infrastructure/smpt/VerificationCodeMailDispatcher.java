package io.syeony.premarket.member.infrastructure.smpt;

public interface VerificationCodeMailDispatcher {

	void dispatch(MailMessage message);
}
