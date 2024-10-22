package io.syeony.premarket.item.persentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.application.dto.EditItemDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditItemRequest(
	@NotBlank(message = "The name field is required")
	String name,

	@Valid
	@JsonProperty(value = "cost")
	@NotNull(message = "The cost field is required")
	CostRequest cost,

	@Min(value = 0, message = "The stock field can’t be less than zero")
	@NotNull(message = "The stock field is required")
	Integer stock,

	@NotBlank(message = "The introduction field is required")
	String introduction,

	@JsonProperty(value = "category_id")
	@NotNull(message = "The category id field is required")
	Long categoryId,

	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id is required")
	String memberId
) {
	public EditItemDto toDto() {
		return EditItemDto.builder()
			.name(name)
			.costDto(new EditItemDto.CostDto(cost.price(), cost.discount()))
			.stock(stock)
			.introduction(introduction)
			.categoryId(categoryId)
			.build();
	}
}
