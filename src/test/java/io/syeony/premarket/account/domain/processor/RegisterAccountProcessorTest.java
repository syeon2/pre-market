package io.syeony.premarket.account.domain.processor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.model.vo.Password;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.account.domain.processor.repository.AccountRepository;
import io.syeony.premarket.account.domain.processor.util.PasswordEncryptor;
import io.syeony.premarket.support.error.exception.DuplicatedEmailException;
import io.syeony.premarket.support.error.exception.DuplicatedPhoneNumberException;

class RegisterAccountProcessorTest extends UnitTestSupport {

	@InjectMocks
	private RegisterAccountProcessor registerAccountProcessor;

	@Mock
	private AccountRepository accountRepository = mock(AccountRepository.class);

	@Mock
	private AccountReader accountReader = mock(AccountReader.class);

	@Mock
	private PasswordEncryptor passwordEncryptor = mock(PasswordEncryptor.class);

	@BeforeEach
	void setUp() {
		registerAccountProcessor = new RegisterAccountProcessor(accountRepository, accountReader, passwordEncryptor);
	}

	@Test
	@DisplayName(value = "Should successfully register when the email and phone number are valid")
	void register_shouldReturnMemberId_whenEmailAndPhoneNumberAreValid() {
		// given
		Account account = mock(Account.class);

		when(account.getEmail()).thenReturn("waterkite94@gmail.com");
		when(account.getPhoneNumber()).thenReturn("01011112222");
		when(account.getPassword()).thenReturn(new Password("rawPassword", null));
		when(passwordEncryptor.encrypt(any())).thenReturn("encryptedPassword");
		when(account.registerWithEncryptedPassword("encryptedPassword")).thenReturn(account);
		when(accountRepository.save(account)).thenReturn(new MemberId("new-member-id"));

		// when
		MemberId result = registerAccountProcessor.register(account);

		// then
		assertNotNull(result);
		assertEquals("new-member-id", result.value());
		verify(accountReader).existsByEmail("waterkite94@gmail.com");
		verify(accountReader).existsByPhoneNumber("01011112222");
		verify(passwordEncryptor).encrypt("rawPassword");
		verify(accountRepository).save(account);
	}

	@Test
	@DisplayName("Should throw DuplicatedEmailException when the email already exists")
	void register_shouldThrowDuplicatedEmailException_whenEmailAlreadyExists() {
		// given
		Account account = mock(Account.class);
		when(account.getEmail()).thenReturn("waterkite94@gmail.com");
		when(accountReader.existsByEmail("waterkite94@gmail.com")).thenReturn(true);

		// when & then
		assertThrows(DuplicatedEmailException.class, () -> {
			registerAccountProcessor.register(account);
		});

		verify(accountReader).existsByEmail("waterkite94@gmail.com");
		verify(accountReader, never()).existsByPhoneNumber(any());
		verify(accountRepository, never()).save(any());
	}

	@Test
	@DisplayName("Should throw DuplicatedPhoneNumberException when the phone number already exists")
	void register_shouldThrowDuplicatedPhoneNumberException_whenPhoneNumberAlreadyExists() {
		// given
		Account account = mock(Account.class);
		when(account.getEmail()).thenReturn("waterkite94@gmail.com");
		when(account.getPhoneNumber()).thenReturn("00011112222");
		when(accountReader.existsByPhoneNumber("00011112222")).thenReturn(true);

		// when & then
		assertThrows(DuplicatedPhoneNumberException.class, () -> {
			registerAccountProcessor.register(account);
		});

		verify(accountReader).existsByEmail("waterkite94@gmail.com");
		verify(accountReader).existsByPhoneNumber("00011112222");
		verify(accountRepository, never()).save(any());
	}

}
