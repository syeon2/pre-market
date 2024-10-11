package io.syeony.premarket.account.domain.processor;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.account.domain.processor.repository.AccountRepository;
import io.syeony.premarket.account.domain.processor.util.PasswordEncryptor;
import io.syeony.premarket.support.error.exception.DuplicatedEmailException;
import io.syeony.premarket.support.error.exception.DuplicatedPhoneNumberException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterAccountProcessor {

	private final AccountRepository accountRepository;
	private final AccountReader accountReader;
	private final PasswordEncryptor passwordEncryptor;

	public MemberId register(Account account) {
		ensureEmailIsUnique(account.getEmail());
		ensurePhoneNumberIsUnique(account.getPhoneNumber());

		return accountRepository.save(
			account.registerWithEncryptedPassword(encryptPassword(account)));
	}

	private void ensureEmailIsUnique(String email) {
		if (accountReader.existsByEmail(email)) {
			throw new DuplicatedEmailException(email);
		}
	}

	private void ensurePhoneNumberIsUnique(String phoneNumber) {
		if (accountReader.existsByPhoneNumber(phoneNumber)) {
			throw new DuplicatedPhoneNumberException(phoneNumber);
		}
	}

	private String encryptPassword(Account account) {
		return passwordEncryptor.encrypt(account.getPassword().rawPassword());
	}
}
