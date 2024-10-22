package io.syeony.premarket.account.infrastructure.smpt;

public record MailMessage(
	String toEmail,
	String subject,
	String body
) {
}
