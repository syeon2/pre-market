package io.syeony.premarket.member.infrastructure.smpt;

public record MailMessage(
	String toEmail,
	String subject,
	String body
) {
}
