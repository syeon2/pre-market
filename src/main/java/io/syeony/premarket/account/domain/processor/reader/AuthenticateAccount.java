package io.syeony.premarket.account.domain.processor.reader;

public interface AuthenticateAccount {

	boolean authenticate(final String email, final String password);
}
