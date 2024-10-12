package io.syeony.premarket.account.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.infrastructure.persistence.entity.AccountEntity;

@Component
public class AccountMapper {

	public AccountEntity toEntity(Account domain) {
		return AccountEntity.builder()
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
}
