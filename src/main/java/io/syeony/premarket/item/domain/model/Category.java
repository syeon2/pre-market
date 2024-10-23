package io.syeony.premarket.item.domain.model;

import io.syeony.premarket.support.common.AuditTimestamps;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Category {

	private Long no;
	private String name;
	private AuditTimestamps auditTimestamps;

	@Builder
	private Category(Long no, String name, AuditTimestamps auditTimestamps) {
		this.no = no;
		this.name = name;
		this.auditTimestamps = auditTimestamps;
	}

	public static Category of(Long no, String name, AuditTimestamps auditTimestamps) {
		return new Category(no, name, auditTimestamps);
	}

	public static Category initializeForCreate(String name) {
		return Category.builder().name(name).build();
	}
}
