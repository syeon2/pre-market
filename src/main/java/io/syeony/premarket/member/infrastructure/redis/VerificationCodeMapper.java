package io.syeony.premarket.member.infrastructure.redis;

import org.springframework.stereotype.Component;

import io.syeony.premarket.member.domain.model.VerificationCode;
import io.syeony.premarket.member.infrastructure.redis.entity.VerificationCodeEntity;

@Component
public class VerificationCodeMapper {

	public VerificationCode toDomain(VerificationCodeEntity entity) {
		return VerificationCode.of(entity.getToEmail(), entity.getCode());
	}

	public VerificationCodeEntity toEntity(VerificationCode domain) {
		return new VerificationCodeEntity(domain.getToEmail(), domain.getCode());
	}
}
