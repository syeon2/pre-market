package io.syeony.premarket.member.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.processor.reader.MemberReader;
import io.syeony.premarket.member.domain.processor.writer.MemberWriter;
import io.syeony.premarket.member.infrastructure.persistence.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberWriter, MemberReader {

	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;

	@Override
	public MemberId save(final Member member) {
		MemberEntity savedEntity = memberRepository.save(memberMapper.toEntity(member));

		return MemberId.of(savedEntity.getMemberId());
	}

	@Override
	public boolean existsByEmail(final String email) {
		return memberRepository.findByEmail(email).isPresent();
	}

	@Override
	public boolean existsByPhoneNumber(final String phoneNumber) {
		return memberRepository.findByPhoneNumber(phoneNumber).isPresent();
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		return memberRepository.findByEmail(email)
			.map(memberMapper::toDomain);
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId).isPresent();
	}
}
