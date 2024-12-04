package io.syeony.premarket.member.infrastructure.redis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "auth:token", timeToLive = 3600)
public class RefreshTokenEntity {
	@Id
	private String email;
	private String token;

	public RefreshTokenEntity(String email, String token) {
		this.email = email;
		this.token = token;
	}
}
