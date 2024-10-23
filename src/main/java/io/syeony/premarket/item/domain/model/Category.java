package io.syeony.premarket.item.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

	private Long no;
	private String name;

	public static Category of(Long no, String name) {
		return new Category(no, name);
	}

	public static Category initNo(Long no) {
		return Category.of(no, null);
	}

	public static Category initializeForCreate(String name) {
		return Category.of(null, name);
	}
}
