package io.syeony.premarket.account.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.syeony.premarket.account.domain.model.vo.Address;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.model.vo.MemberRole;
import io.syeony.premarket.account.domain.model.vo.Password;
import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;

class AccountTest {

	@Test
	@DisplayName(value = "Generate account domain successfully")
	void generateAccount_shouldGenerateAccountSuccessfully() {
		// given
		MemberId memberId = MemberId.of("memberId");
		String email = "waterkite94@gmail.com";
		Password password = new Password("rawPassword", "encryptedPassword");
		String name = "suyeon";
		String phoneNumber = "00011112222";
		Address address = new Address("address1", "address2", "zipcode");
		MemberRole role = MemberRole.NORMAL_CUSTOMER;
		AuditTimestamps auditTimestamps = new AuditTimestamps(LocalDateTime.now(), LocalDateTime.now());

		// when
		Account account = createAccountDomain(
			memberId, email, password, name, phoneNumber, address, role, auditTimestamps);

		// then
		assertThat(account.getId()).isEqualTo(1L);
		assertThat(account.getMemberId()).isEqualTo(memberId);
		assertThat(account.getEmail()).isEqualTo(email);
		assertThat(account.getPassword()).isEqualTo(password);
		assertThat(account.getName()).isEqualTo(name);
		assertThat(account.getPhoneNumber()).isEqualTo(phoneNumber);
		assertThat(account.getAddress()).isEqualTo(address);
		assertThat(account.getRole()).isEqualTo(role);
		assertThat(account.getAuditTimestamps()).isEqualTo(auditTimestamps);
		assertThat(account.getStatus()).isEqualTo(EntityStatus.ALIVE);
	}

	@Test
	@DisplayName(value = "Register with encrypted password")
	void registerWithEncryptedPassword_shouldReturnNewAccountWithEncodedPassword() {
		// given
		String email = "waterkite94@gmail.com";
		Password password = new Password("rawPassword", null);
		String name = "suyeon";
		String phoneNumber = "00011112222";
		Address address = new Address("address1", "address2", "zipcode");
		MemberRole role = MemberRole.NORMAL_CUSTOMER;

		Account account = createAccountDomain(
			MemberId.of("memberId"), email, password, name, phoneNumber, address, role, null);

		String newEncodedPassword = "encryptedPassword";

		// when
		Account newAccount = account.registerWithEncryptedPassword(newEncodedPassword);

		// then
		assertThat(newAccount).isNotSameAs(account);
		assertThat(newAccount.getPassword().encryptPassword()).isEqualTo(newEncodedPassword);
		assertThat(newAccount.getEmail()).isEqualTo(email);
		assertThat(newAccount.getMemberId()).isNotNull();
		assertThat(newAccount.getRole()).isEqualTo(MemberRole.NORMAL_CUSTOMER);
		assertThat(newAccount.getStatus()).isEqualTo(EntityStatus.ALIVE);
	}

	@Test
	@DisplayName("Generate member id successfully")
	void generateMemberId_shouldGenerateUniqueMemberId() {
		// when
		MemberId memberId1 = MemberId.of(UUID.randomUUID().toString());
		MemberId memberId2 = MemberId.of(UUID.randomUUID().toString());

		// then
		assertThat(memberId1).isNotNull();
		assertThat(memberId2).isNotNull();
		assertThat(memberId1).isNotEqualTo(memberId2);
	}

	private Account createAccountDomain(MemberId memberId, String email, Password password, String name,
		String phoneNumber, Address address, MemberRole role, AuditTimestamps auditTimestamps) {
		return Account.builder()
			.id(1L)
			.memberId(memberId)
			.email(email)
			.password(password)
			.name(name)
			.phoneNumber(phoneNumber)
			.address(address)
			.role(role)
			.auditTimestamps(auditTimestamps)
			.status(EntityStatus.ALIVE)
			.build();
	}
}
