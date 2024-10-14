package io.syeony.premarket.account.infrastructure.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "account:code", timeToLive = 300)
public class VerificationCodeEntity {

	@Id
	private String email;
	private String code;

	public VerificationCodeEntity(String email, String code) {
		this.email = email;
		this.code = code;
	}
}
