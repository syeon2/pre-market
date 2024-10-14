package io.syeony.premarket.account.infrastructure.smpt;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SmtpMailDispatcher implements MailDispatcher {

	private final JavaMailSender javaMailSender;

	@Override
	public void dispatch(MailMessage message) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			helper.setTo(message.toEmail());
			helper.setSubject(message.subject());
			helper.setText(message.body(), true);

			javaMailSender.send(mimeMessage);

			log.info("sent email: {}", message.body());
		} catch (MessagingException e) {
			log.error("failed to send email", e);
			throw new RuntimeException(e);
		}
	}
}
