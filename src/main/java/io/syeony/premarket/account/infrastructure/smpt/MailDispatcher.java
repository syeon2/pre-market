package io.syeony.premarket.account.infrastructure.smpt;

public interface MailDispatcher {

	void dispatch(MailMessage message);
}
