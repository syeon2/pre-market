package io.syeony.premarket.inquire.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class InquiredItem {

	private Long itemNo;
	private String itemName;

	public static InquiredItem of(Long itemNo, String itemName) {
		return new InquiredItem(itemNo, itemName);
	}
}
