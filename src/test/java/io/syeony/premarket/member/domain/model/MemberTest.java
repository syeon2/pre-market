package io.syeony.premarket.member.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.syeony.premarket.member.domain.model.vo.Address;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.model.vo.MemberRole;
import io.syeony.premarket.member.domain.model.vo.Password;
import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;

class MemberTest {

	@Test
	@DisplayName(value = "Generate member domain successfully")
	void generateMember_shouldGenerateMemberSuccessfully() {
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
		Member member = createMemberDomain(
			memberId, email, password, name, phoneNumber, address, role, auditTimestamps);

		// then
		assertThat(member.getId()).isEqualTo(1L);
		assertThat(member.getMemberId()).isEqualTo(memberId);
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getPassword()).isEqualTo(password);
		assertThat(member.getName()).isEqualTo(name);
		assertThat(member.getPhoneNumber()).isEqualTo(phoneNumber);
		assertThat(member.getAddress()).isEqualTo(address);
		assertThat(member.getRole()).isEqualTo(role);
		assertThat(member.getAuditTimestamps()).isEqualTo(auditTimestamps);
		assertThat(member.getStatus()).isEqualTo(EntityStatus.ALIVE);
	}

	@Test
	@DisplayName(value = "Register with encrypted password")
	void register_shouldReturnNewWithEncodedPassword() {
		// given
		String email = "waterkite94@gmail.com";
		Password password = new Password("rawPassword", null);
		String name = "suyeon";
		String phoneNumber = "00011112222";
		Address address = new Address("address1", "address2", "zipcode");
		MemberRole role = MemberRole.NORMAL_CUSTOMER;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Member member = createMemberDomain(
			MemberId.of("memberId"), email, password, name, phoneNumber, address, role, null);

		// when
		Member newMember = member.register(passwordEncoder::encode);

		// then
		assertThat(newMember).isNotSameAs(member);
		assertThat(newMember.getPassword().encryptPassword()).isNotEqualTo(password.rawPassword());
		assertThat(newMember.getEmail()).isEqualTo(email);
		assertThat(newMember.getMemberId()).isNotNull();
		assertThat(newMember.getRole()).isEqualTo(MemberRole.NORMAL_CUSTOMER);
		assertThat(newMember.getStatus()).isEqualTo(EntityStatus.ALIVE);
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

	private Member createMemberDomain(MemberId memberId, String email, Password password, String name,
		String phoneNumber, Address address, MemberRole role, AuditTimestamps auditTimestamps) {
		return Member.builder()
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
