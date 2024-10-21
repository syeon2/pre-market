package io.syeony.premarket.account.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.account.domain.model.RefreshToken;

public interface RefreshTokenReader {

	Optional<RefreshToken> findByEmail(String email);
}
