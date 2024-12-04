package io.syeony.premarket.member.infrastructure.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "member:code")
public class VerificationCodeEntity {

	@Id
	private String toEmail;
	private final String code;

	public VerificationCodeEntity(String toEmail, String code) {
		this.toEmail = toEmail;
		this.code = code;
	}
}
