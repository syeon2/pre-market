package io.syeony.premarket.member.domain.processor.reader;

public interface AuthenticateMember {

	boolean authenticate(final String email, final String password);
}
