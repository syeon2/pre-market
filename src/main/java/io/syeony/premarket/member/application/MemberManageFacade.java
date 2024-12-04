package io.syeony.premarket.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.processor.RegisterMemberProcessor;
import io.syeony.premarket.member.domain.processor.VerificationCodeVerifier;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberManageFacade {

	private final RegisterMemberProcessor registerMemberProcessor;
	private final VerificationCodeVerifier verificationCodeVerifier;

	@Transactional
	public MemberId registerMember(final Member member, final String verificationCode) {
		verificationCodeVerifier.verify(member.getEmail(), verificationCode);
		return registerMemberProcessor.register(member);
	}

}
