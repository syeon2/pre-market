package io.syeony.premarket.support.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailConfiguration {

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String startTslEnable;

	@Value("${spring.mail.properties.mail.smtp.starttls.required}")
	private String startTslRequired;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setDefaultEncoding("UTF-8");
		javaMailSender.setJavaMailProperties(getMailProperties());

		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", startTslEnable);
		properties.put("mail.smtp.starttls.required", startTslRequired);

		return properties;
	}
}
