package io.syeony.premarket.account.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.account.domain.processor.repository.AccountRepository;
import io.syeony.premarket.account.infrastructure.persistence.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountRepository, AccountReader {

	private final JpaAccountRepository accountRepository;
	private final AccountMapper accountMapper;

	@Override
	public MemberId save(final Account account) {
		MemberEntity savedEntity = accountRepository.save(accountMapper.toEntity(account));

		return MemberId.of(savedEntity.getMemberId());
	}

	@Override
	public boolean existsByEmail(final String email) {
		return accountRepository.findByEmail(email).isPresent();
	}

	@Override
	public boolean existsByPhoneNumber(final String phoneNumber) {
		return accountRepository.findByPhoneNumber(phoneNumber).isPresent();
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		Optional<MemberEntity> entity = accountRepository.findByEmail(email);
		return entity.map(accountMapper::toDomain);
	}
}
