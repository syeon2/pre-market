package io.syeony.premarket.member.infrastructure.persistence;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.JpaInfraTestSupport;
import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.Address;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.model.vo.MemberRole;
import io.syeony.premarket.member.domain.model.vo.Password;
import io.syeony.premarket.member.infrastructure.persistence.entity.MemberEntity;
import io.syeony.premarket.support.common.EntityStatus;

@Import({
	MemberPersistenceAdapter.class,
	MemberMapper.class
})
class MemberPersistenceAdapterTest extends JpaInfraTestSupport {

	@Autowired
	private MemberPersistenceAdapter memberPersistenceAdapter;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName(value = "Save member and return MemberId")
	void save() {
		// given
		Member member = createMemberDomain();

		// when
		MemberId savedEntity = memberPersistenceAdapter.save(member);

		// then
		assertThat(savedEntity.value()).isEqualTo(member.getMemberId().value());
	}

	@Test
	@DisplayName(value = "Check if email exists and return true")
	void existsByEmail_shouldReturnTrue() {
		// given
		String email = "waterkite94@gmail.com";
		memberRepository.save(createMemberEntity(email, "00011112222"));

		// when
		boolean result = memberPersistenceAdapter.existsByEmail(email);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName(value = "Check if email does not exist and return false")
	void existsByEmail_shouldReturnFalse() {
		// When
		boolean result = memberPersistenceAdapter.existsByEmail("notexist@example.com");

		// Then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName(value = "Check if phone number exists and return true")
	void existsByPhoneNumber_shouldReturnTrue() {
		// Given
		String phoneNumber = "00011112222";
		memberRepository.save(createMemberEntity("waterkite94@gmail.com", phoneNumber));

		// When
		boolean result = memberPersistenceAdapter.existsByPhoneNumber(phoneNumber);

		// Then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("Check if phone number does not exist and return false")
	void existsByPhoneNumber_shouldReturnFalse() {
		// When
		boolean result = memberPersistenceAdapter.existsByPhoneNumber("00011112222");

		// Then
		assertThat(result).isFalse();
	}

	private MemberEntity createMemberEntity(String email, String phoneNumber) {
		return MemberEntity.builder()
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

	private Member createMemberDomain() {
		return Member.builder()
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
