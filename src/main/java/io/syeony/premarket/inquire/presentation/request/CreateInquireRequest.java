package io.syeony.premarket.inquire.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.inquire.domain.model.Inquire;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateInquireRequest(
	@NotBlank(message = "The message field is required")
	String message,

	@JsonProperty(value = "item_no")
	@NotNull(message = "The item no field is required")
	Long itemNo,

	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id field is required")
	String memberId
) {
	public Inquire toDomain() {
		return Inquire.initializeForCreate(message, memberId, itemNo);
	}
}
