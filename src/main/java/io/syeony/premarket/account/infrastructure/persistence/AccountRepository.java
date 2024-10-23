package io.syeony.premarket.account.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.account.infrastructure.persistence.entity.MemberEntity;

public interface AccountRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByEmail(String email);

	Optional<MemberEntity> findByPhoneNumber(String phoneNumber);

	Optional<MemberEntity> findByMemberId(String memberId);
}

