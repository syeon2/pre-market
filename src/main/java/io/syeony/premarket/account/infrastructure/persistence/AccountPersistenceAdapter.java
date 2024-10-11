package io.syeony.premarket.account.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.account.domain.processor.repository.AccountRepository;

@Repository
public class AccountPersistenceAdapter implements AccountRepository, AccountReader {

	@Override
	public boolean existsByEmail(String email) {
		return false;
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return false;
	}

	@Override
	public MemberId save(Account account) {
		return null;
	}
}
