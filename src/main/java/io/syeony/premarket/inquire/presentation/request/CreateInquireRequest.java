package io.syeony.premarket.inquire.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.inquire.domain.model.Inquire;
import jakarta.validation.constraints.NotBlank;

public record CreateInquireRequest(
	@NotBlank(message = "The comment field is required")
	String comment,

	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id field is required")
	String memberId
) {
	public Inquire toDomain() {
		return Inquire.initializeForCreate(this.comment, this.memberId);
	}
}
