package io.syeony.premarket.item.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Seller {

	private Long memberNo;
	private String memberId;
	private String name;

	@Builder
	public Seller(Long memberNo, String memberId, String name) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.name = name;
	}

	public static Seller of(Long memberNo, String memberId, String name) {
		return new Seller(memberNo, memberId, name);
	}

	public static Seller initId(String memberId) {
		return new Seller(null, memberId, null);
	}
}
