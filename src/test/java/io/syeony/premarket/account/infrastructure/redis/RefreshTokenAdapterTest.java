package io.syeony.premarket.account.infrastructure.redis;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.RedisInfraTestSupport;
import io.syeony.premarket.account.domain.model.RefreshToken;
import io.syeony.premarket.account.domain.model.RefreshTokenEvent;

@Import(RefreshTokenAdapter.class)
class RefreshTokenAdapterTest extends RedisInfraTestSupport {

	@Autowired
	private RefreshTokenAdapter refreshTokenAdapter;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Test
	@DisplayName(value = "Save Refresh token by event listener and find token by email")
	void handleRefreshTokenAndFindTokenByEmail() {
		// given
		String refreshToken = "refresh_token_value";
		RefreshTokenEvent event = new RefreshTokenEvent("waterkite94@gmail.com", refreshToken);

		// when
		refreshTokenAdapter.handleRefreshToken(event);

		// then
		Optional<RefreshToken> findToken = refreshTokenAdapter.findByEmail(event.email());
		assertThat(findToken.isPresent()).isTrue();
		assertThat(findToken.get().getRefreshToken()).isEqualTo(refreshToken);
	}
}
