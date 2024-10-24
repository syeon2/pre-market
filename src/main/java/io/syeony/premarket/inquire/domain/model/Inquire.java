package io.syeony.premarket.inquire.domain.model;

import io.syeony.premarket.support.common.AuditTimestamps;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Inquire {

	private Long inquireNo;
	private String message;
	private Writer writer;
	private InquiredItem inquiredItem;
	private AuditTimestamps auditTimestamps;

	public static Inquire of(Long inquireNo, String comment, Writer writer,
		InquiredItem inquiredItem, AuditTimestamps auditTimestamps) {
		return new Inquire(inquireNo, comment, writer, inquiredItem, auditTimestamps);
	}

	public static Inquire initializeForCreate(String message, String memberId, Long itemNo) {
		return new Inquire(null, message, Writer.of(memberId, null), InquiredItem.of(itemNo, null), null);
	}

	public boolean validate(Long itemNo, String memberId) {
		return inquiredItem.getItemNo().equals(itemNo) && writer.getMemberId().equals(memberId);
	}
}
