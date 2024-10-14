package io.syeony.premarket.account.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.JpaInfraTestSupport;
import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.Address;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.model.vo.MemberRole;
import io.syeony.premarket.account.domain.model.vo.Password;
import io.syeony.premarket.account.infrastructure.persistence.entity.AccountEntity;
import io.syeony.premarket.support.common.EntityStatus;

@Import({
	AccountPersistenceAdapter.class,
	AccountMapper.class
})
class AccountPersistenceAdapterTest extends JpaInfraTestSupport {

	@Autowired
	private AccountPersistenceAdapter accountPersistenceAdapter;

	@Autowired
	private JpaAccountRepository jpaAccountRepository;

	@Test
	@DisplayName(value = "Save account and return MemberId")
	void save() {
		// given
		Account account = createAccountDomain();

		// when
		MemberId savedEntity = accountPersistenceAdapter.save(account);

		// then
		assertThat(savedEntity.value()).isEqualTo(account.getMemberId().value());
	}

	@Test
	@DisplayName(value = "Check if email exists and return true")
	void existsByEmail_shouldReturnTrue() {
		// given
		String email = "waterkite94@gmail.com";
		jpaAccountRepository.save(createAccountEntity(email, "00011112222"));

		// when
		boolean result = accountPersistenceAdapter.existsByEmail(email);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName(value = "Check if email does not exist and return false")
	void existsByEmail_shouldReturnFalse() {
		// When
		boolean result = accountPersistenceAdapter.existsByEmail("notexist@example.com");

		// Then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName(value = "Check if phone number exists and return true")
	void existsByPhoneNumber_shouldReturnTrue() {
		// Given
		String phoneNumber = "00011112222";
		jpaAccountRepository.save(createAccountEntity("waterkite94@gmail.com", phoneNumber));

		// When
		boolean result = accountPersistenceAdapter.existsByPhoneNumber(phoneNumber);

		// Then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("Check if phone number does not exist and return false")
	void existsByPhoneNumber_shouldReturnFalse() {
		// When
		boolean result = accountPersistenceAdapter.existsByPhoneNumber("00011112222");

		// Then
		assertThat(result).isFalse();
	}

	private AccountEntity createAccountEntity(String email, String phoneNumber) {
		return AccountEntity.builder()
			.memberId("memberId")
			.email(email)
			.password("encryptedPassword")
			.name("suyoen")
			.phoneNumber(phoneNumber)
			.baseAddress("address1")
			.addressDetail("address2")
			.zipcode("000111")
			.role(MemberRole.NORMAL_CUSTOMER)
			.status(EntityStatus.ALIVE)
			.build();
	}

	private Account createAccountDomain() {
		return Account.builder()
			.memberId(MemberId.of("memberId"))
			.email("waterkite@gmail.com")
			.password(new Password("rawPassword", "encryptedPassword"))
			.name("suyeon")
			.phoneNumber("00011122223")
			.address(new Address("address1", "address2", "zipcode"))
			.role(MemberRole.NORMAL_CUSTOMER)
			.status(EntityStatus.ALIVE)
			.build();
	}

}
