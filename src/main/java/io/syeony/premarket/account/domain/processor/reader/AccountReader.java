package io.syeony.premarket.account.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.account.domain.model.Account;

public interface AccountReader {

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<Account> findByEmail(String email);

	boolean existsByMemberId(String memberId);
}
