package io.syeony.premarket.inquire.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public final class Writer {
	private String memberId;
	private String name;

	public Writer(String memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}

	public static Writer of(String memberId, String name) {
		return new Writer(memberId, name);
	}
}
