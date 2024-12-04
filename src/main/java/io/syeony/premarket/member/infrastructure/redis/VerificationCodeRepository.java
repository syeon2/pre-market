package io.syeony.premarket.member.infrastructure.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.member.infrastructure.redis.entity.VerificationCodeEntity;

@Repository
public interface VerificationCodeRepository extends CrudRepository<VerificationCodeEntity, Long> {

	Optional<VerificationCodeEntity> findByToEmail(String email);
}
