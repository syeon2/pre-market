package io.syeony.premarket.account.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.account.domain.processor.writer.AccountWriter;
import io.syeony.premarket.account.infrastructure.persistence.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountWriter, AccountReader {

	private final AccountRepository accountRepository;
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
		return accountRepository.findByEmail(email)
			.map(accountMapper::toDomain);
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		return accountRepository.findByMemberId(memberId).isPresent();
	}
}
