package io.syeony.premarket.member.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.member.domain.model.RefreshToken;

public interface RefreshTokenReader {

	Optional<RefreshToken> findByEmail(String email);
}
