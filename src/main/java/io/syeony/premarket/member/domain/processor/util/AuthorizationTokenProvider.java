package io.syeony.premarket.member.domain.processor.util;

import io.syeony.premarket.member.domain.model.AuthorizationToken;

public interface AuthorizationTokenProvider {

	AuthorizationToken generateAuthorizeToken(String email);
}
