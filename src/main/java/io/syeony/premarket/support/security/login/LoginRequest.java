package io.syeony.premarket.support.security.login;

public record LoginRequest(
	String email,
	String password
) {
}
