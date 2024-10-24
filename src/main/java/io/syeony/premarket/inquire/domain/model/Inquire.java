package io.syeony.premarket.inquire.domain.model;

import io.syeony.premarket.support.common.AuditTimestamps;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Inquire {

	private Long inquireNo;
	private String comment;
	private Writer writer;
	private AuditTimestamps auditTimestamps;

	public static Inquire of(Long inquireNo, String comment, Writer writer, AuditTimestamps auditTimestamps) {
		return new Inquire(inquireNo, comment, writer, auditTimestamps);
	}

	public static Inquire initializeForCreate(String comment, String memberId) {
		return new Inquire(null, comment, Writer.of(memberId, null), null);
	}
}
