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

	public MemberId register(final Account account) {
		checkEmailIsUnique(account.getEmail());
		checkPhoneNumberIsUnique(account.getPhoneNumber());

		String encodedPassword = encryptPassword(account.getPassword().rawPassword());
		return accountRepository.save(
			account.registerWithEncryptedPassword(encodedPassword));
	}

	private void checkEmailIsUnique(final String email) {
		if (accountReader.existsByEmail(email)) {
			throw new DuplicatedEmailException(email);
		}
	}

	private void checkPhoneNumberIsUnique(final String phoneNumber) {
		if (accountReader.existsByPhoneNumber(phoneNumber)) {
			throw new DuplicatedPhoneNumberException(phoneNumber);
		}
	}

	private String encryptPassword(final String rawPassword) {
		return passwordEncryptor.encrypt(rawPassword);
	}
}
