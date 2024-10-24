package io.syeony.premarket.inquire.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.inquire.domain.model.InquireComment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateInquireCommentRequest(
	@NotBlank(message = "The message field is required")
	String message,

	@JsonProperty(value = "inquire_no")
	@NotNull(message = "The inquire no field is required")
	Long inquireNo
) {
	public InquireComment toDomain() {
		return InquireComment.initializeForCreate(message, inquireNo);
	}
}
