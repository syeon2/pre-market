package io.syeony.premarket.item.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Seller {

	private String memberNo;
	private String memberId;
	private String name;

	@Builder
	public Seller(String memberNo, String memberId, String name) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.name = name;
	}
}
