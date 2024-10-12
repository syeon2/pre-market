package io.syeony.premarket.account.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.account.domain.processor.repository.AccountRepository;
import io.syeony.premarket.account.infrastructure.persistence.entity.AccountEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountRepository, AccountReader {

	private final JpaAccountRepository accountRepository;
	private final AccountMapper accountMapper;

	@Override
	public MemberId save(Account account) {
		AccountEntity savedEntity = accountRepository.save(accountMapper.toEntity(account));

		return MemberId.of(savedEntity.getMemberId());
	}

	@Override
	public boolean existsByEmail(String email) {
		return accountRepository.findByEmail(email).isPresent();
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return accountRepository.findByPhoneNumber(phoneNumber).isPresent();
	}
}
