package io.syeony.premarket.member.domain.processor.writer;

import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.MemberId;

public interface MemberWriter {

	MemberId save(Member member);
}
