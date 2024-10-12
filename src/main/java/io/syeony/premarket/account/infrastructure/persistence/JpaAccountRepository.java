package io.syeony.premarket.account.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.account.infrastructure.persistence.entity.AccountEntity;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

	Optional<AccountEntity> findByEmail(String email);

	Optional<AccountEntity> findByPhoneNumber(String phoneNumber);
}

