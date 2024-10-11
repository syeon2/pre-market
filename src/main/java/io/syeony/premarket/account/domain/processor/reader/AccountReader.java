package io.syeony.premarket.account.domain.processor.reader;

public interface AccountReader {

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);
}
