package io.syeony.premarket.inquire.presentation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.inquire.domain.model.Inquire;

public record FindInquireResponse(
	@JsonProperty(value = "inquire_no")
	Long inquireNo,

	String message,

	@JsonProperty(value = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDateTime createdAt,

	@JsonProperty(value = "updated_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDateTime updateAt,

	@JsonProperty(value = "writer")
	WriterResponse writer
) {
	public static FindInquireResponse from(Inquire inquire) {
		return new FindInquireResponse(
			inquire.getInquireNo(),
			inquire.getMessage(),
			inquire.getAuditTimestamps().getCreatedAt(),
			inquire.getAuditTimestamps().getUpdatedAt(),
			new WriterResponse(
				inquire.getWriter().getMemberId(),
				inquire.getWriter().getName()
			)
		);
	}
}
