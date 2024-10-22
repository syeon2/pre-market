package io.syeony.premarket.item.domain.model;

import io.syeony.premarket.support.common.AuditTimestamps;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Category {

	private Long id;
	private String name;
	private AuditTimestamps auditTimestamps;

	@Builder
	private Category(Long id, String name, AuditTimestamps auditTimestamps) {
		this.id = id;
		this.name = name;
		this.auditTimestamps = auditTimestamps;
	}

	public static Category initialize(String name) {
		return Category.builder().name(name).build();
	}
}
