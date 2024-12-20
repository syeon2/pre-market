package io.syeony.premarket.member.infrastructure.redis;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.event.TransactionalEventListener;

import io.syeony.premarket.member.domain.model.RefreshToken;
import io.syeony.premarket.member.domain.model.RefreshTokenEvent;
import io.syeony.premarket.member.domain.processor.reader.RefreshTokenReader;
import io.syeony.premarket.member.infrastructure.redis.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenReader {

	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public Optional<RefreshToken> findByEmail(String email) {
		return refreshTokenRepository.findByEmail(email)
			.map(token -> new RefreshToken(token.getToken()));
	}

	@TransactionalEventListener
	public void handleRefreshToken(RefreshTokenEvent event) {
		refreshTokenRepository.save(new RefreshTokenEntity(event.email(), event.refreshToken()));
	}
}
