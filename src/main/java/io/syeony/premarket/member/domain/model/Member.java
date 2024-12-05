package io.syeony.premarket.member.domain.model;

import java.util.UUID;
import java.util.function.Function;

import io.syeony.premarket.member.domain.model.vo.Address;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.model.vo.MemberRole;
import io.syeony.premarket.member.domain.model.vo.Password;
import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "memberId")
public final class Member {

	private Long id;
	private MemberId memberId;
	private String email;
	private Password password;
	private String name;
	private String phoneNumber;
	private Address address;
	private MemberRole role;
	private AuditTimestamps auditTimestamps;
	private EntityStatus status;

	@Builder
	private Member(Long id, MemberId memberId, String email, Password password, String name, String phoneNumber,
		Address address, MemberRole role, AuditTimestamps auditTimestamps, EntityStatus status) {
		this.id = id;
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
		this.auditTimestamps = auditTimestamps;
		this.status = status != null ? status : EntityStatus.ALIVE;
	}

	public Member register(Function<String, String> encrypt) {
		return Member.builder()
			.memberId(generateMemberId())
			.email(email)
			.password(encryptPassword(encrypt))
			.name(name)
			.phoneNumber(phoneNumber)
			.address(address)
			.role(MemberRole.NORMAL_CUSTOMER)
			.status(EntityStatus.ALIVE)
			.build();
	}

	private MemberId generateMemberId() {
		return MemberId.of(UUID.randomUUID().toString());
	}

	private Password encryptPassword(Function<String, String> encrypt) {
		String rawPassword = this.password.rawPassword();
		return Password.from(rawPassword, encrypt.apply(rawPassword));
	}
}
