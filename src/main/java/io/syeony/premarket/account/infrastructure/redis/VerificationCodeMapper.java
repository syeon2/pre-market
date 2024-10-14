package io.syeony.premarket.account.infrastructure.redis;

import org.springframework.stereotype.Component;

import io.syeony.premarket.account.domain.model.VerificationCode;
import io.syeony.premarket.account.infrastructure.redis.entity.VerificationCodeEntity;

@Component
public class VerificationCodeMapper {

	public VerificationCode toDomain(VerificationCodeEntity entity) {
		return VerificationCode.of(entity.getToEmail(), entity.getCode());
	}
}
