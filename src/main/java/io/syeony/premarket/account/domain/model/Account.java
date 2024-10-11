package io.syeony.premarket.account.domain.model;

import java.util.UUID;

import io.syeony.premarket.account.domain.model.vo.Address;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.model.vo.MemberRole;
import io.syeony.premarket.account.domain.model.vo.Password;
import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "memberId")
public final class Account {

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
	private Account(Long id, MemberId memberId, String email, Password password, String name, String phoneNumber,
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

	public Account registerWithEncryptedPassword(String encodedPassword) {
		return Account.builder()
			.memberId(generateMemberId())
			.email(email)
			.password(new Password(this.password.rawPassword(), encodedPassword))
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
}
