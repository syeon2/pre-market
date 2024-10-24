package io.syeony.premarket.inquire.domain.model;

import io.syeony.premarket.support.common.AuditTimestamps;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InquireComment {

	private Long commentNo;
	private String message;
	private Long inquireNo;
	private AuditTimestamps auditTimestamps;

	public static InquireComment of(Long commentNo, String message, Long inquireNo) {
		return new InquireComment(commentNo, message, inquireNo, new AuditTimestamps());
	}

	public static InquireComment initializeForCreate(String message, Long inquireNo) {
		return new InquireComment(null, message, inquireNo, null);
	}
}
