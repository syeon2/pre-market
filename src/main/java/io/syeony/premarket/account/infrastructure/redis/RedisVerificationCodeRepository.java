package io.syeony.premarket.account.infrastructure.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.infrastructure.redis.entity.VerificationCodeEntity;

@Repository
public interface RedisVerificationCodeRepository extends CrudRepository<VerificationCodeEntity, Long> {

	Optional<VerificationCodeEntity> findByToEmail(String email);
}
