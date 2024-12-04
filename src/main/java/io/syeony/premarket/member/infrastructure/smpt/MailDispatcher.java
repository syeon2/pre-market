package io.syeony.premarket.member.infrastructure.smpt;

public interface MailDispatcher {

	void dispatch(MailMessage message);
}
