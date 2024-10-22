package io.syeony.premarket.account.domain.processor.util;

import io.syeony.premarket.account.domain.model.AuthorizationToken;

public interface AuthorizationTokenProvider {

	AuthorizationToken generateAuthorizeToken(String email);
}
