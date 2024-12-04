package io.syeony.premarket.member.domain.processor.reader;

import java.util.Optional;

import io.syeony.premarket.member.domain.model.Member;

public interface MemberReader {

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<Member> findByEmail(String email);

	boolean existsByMemberId(String memberId);
}
