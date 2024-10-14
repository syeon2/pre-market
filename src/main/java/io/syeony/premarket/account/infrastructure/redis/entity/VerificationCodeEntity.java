package io.syeony.premarket.account.infrastructure.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "account:code", timeToLive = 300)
public class VerificationCodeEntity {

	@Id
	private String toEmail;
	private String code;

	public VerificationCodeEntity(String toEmail, String code) {
		this.toEmail = toEmail;
		this.code = code;
	}
}
