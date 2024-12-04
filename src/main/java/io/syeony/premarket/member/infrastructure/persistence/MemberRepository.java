package io.syeony.premarket.member.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.member.infrastructure.persistence.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByEmail(String email);

	Optional<MemberEntity> findByPhoneNumber(String phoneNumber);

	Optional<MemberEntity> findByMemberId(String memberId);
}

