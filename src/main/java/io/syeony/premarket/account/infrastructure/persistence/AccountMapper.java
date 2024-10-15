package io.syeony.premarket.account.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.Address;
import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.domain.model.vo.Password;
import io.syeony.premarket.account.infrastructure.persistence.entity.MemberEntity;
import io.syeony.premarket.support.common.AuditTimestamps;

@Component
public class AccountMapper {

	public MemberEntity toEntity(final Account domain) {
		return MemberEntity.builder()
			.memberId(domain.getMemberId().value())
			.email(domain.getEmail())
			.password(domain.getPassword().encryptPassword())
			.name(domain.getName())
			.phoneNumber(domain.getPhoneNumber())
			.baseAddress(domain.getAddress().baseAddress())
			.addressDetail(domain.getAddress().addressDetails())
			.zipcode(domain.getAddress().zipcode())
			.role(domain.getRole())
			.status(domain.getStatus())
			.build();
	}

	public Account toDomain(final MemberEntity entity) {
		return Account.builder()
			.id(entity.getId())
			.memberId(MemberId.of(entity.getMemberId()))
			.email(entity.getEmail())
			.password(new Password(null, entity.getPassword()))
			.name(entity.getName())
			.phoneNumber(entity.getPhoneNumber())
			.address(new Address(entity.getBaseAddress(), entity.getAddressDetail(), entity.getZipcode()))
			.role(entity.getRole())
			.status(entity.getStatus())
			.auditTimestamps(new AuditTimestamps(entity.getCreatedAt(), entity.getUpdatedAt()))
			.build();
	}
}
