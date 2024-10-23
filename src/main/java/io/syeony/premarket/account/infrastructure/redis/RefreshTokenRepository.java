package io.syeony.premarket.account.infrastructure.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.infrastructure.redis.entity.RefreshTokenEntity;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, Long> {

	Optional<RefreshTokenEntity> findByEmail(String email);
}
