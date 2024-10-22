package io.syeony.premarket.account.infrastructure.redis;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.event.TransactionalEventListener;

import io.syeony.premarket.account.domain.model.RefreshToken;
import io.syeony.premarket.account.domain.model.RefreshTokenEvent;
import io.syeony.premarket.account.domain.processor.reader.RefreshTokenReader;
import io.syeony.premarket.account.infrastructure.redis.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenReader {

	private final RedisRefreshTokenRepository redisRefreshTokenRepository;

	@Override
	public Optional<RefreshToken> findByEmail(String email) {
		return redisRefreshTokenRepository.findByEmail(email)
			.map(token -> new RefreshToken(token.getToken()));
	}

	@TransactionalEventListener
	public void handleRefreshToken(RefreshTokenEvent event) {
		redisRefreshTokenRepository.save(new RefreshTokenEntity(event.email(), event.refreshToken()));
	}
}
