package io.syeony.premarket.item.persentation.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.application.dto.AddItemDto;
import io.syeony.premarket.item.domain.model.ItemType;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterItemRequest(

	@JsonProperty(value = "member_id")
	@NotBlank(message = "The member id is required")
	String memberId,

	@NotBlank(message = "The name field is required")
	String name,

	@Valid
	@JsonProperty(value = "cost")
	@NotNull(message = "The cost field is required")
	CostRequest costRequest,

	@Min(value = 0, message = "The stock field can’t be less than zero")
	@NotNull(message = "The stock field is required")
	Integer stock,

	@NotBlank(message = "The introduction field is required")
	String introduction,

	@JsonProperty(value = "item_type")
	@NotNull(message = "The item type field is required")
	ItemTypeRequest itemTypeRequest,

	@Valid
	@Nullable
	@JsonProperty(value = "pre_order_schedule")
	PreOrderScheduleRequest preOrderScheduleRequest,

	@JsonProperty(value = "category_id")
	@NotNull(message = "The category id field is required")
	Long categoryId
) {
	public AddItemDto toDto() {
		return AddItemDto.builder()
			.memberId(memberId)
			.name(name)
			.costDto(new AddItemDto.CostDto(costRequest.price, costRequest.discount))
			.stock(stock)
			.introduction(introduction)
			.itemType(itemTypeRequest.toDto())
			.preOrderSchedule(toLocalDateTime())
			.categoryId(categoryId)
			.build();
	}

	private LocalDateTime toLocalDateTime() {
		return preOrderScheduleRequest == null ? null :
			LocalDateTime.of(
				preOrderScheduleRequest.year,
				preOrderScheduleRequest.month,
				preOrderScheduleRequest.date,
				preOrderScheduleRequest.hour,
				preOrderScheduleRequest.minute);
	}

	public record CostRequest(
		@Min(value = 1, message = "The price field can't be less than 1")
		@NotNull(message = "The price field is required")
		Integer price,

		@Min(value = 0, message = "The discount field can't be less than 0")
		@NotNull(message = "The discount field is required")
		Integer discount
	) {
	}

	public record PreOrderScheduleRequest(
		@NotNull(message = "The year field is required")
		@Min(value = 2020, message = "The year in the pre order schedule field can't be less than 2020")
		@Max(value = 2100, message = "The year in the pre order schedule field can't be more than 2100")
		Integer year,

		@NotNull(message = "The month field is required")
		@Min(value = 1, message = "The month in the pre order schedule field can be between 1 and 20")
		@Max(value = 12, message = "The month in the pre order schedule field can be between 1 and 20")
		Integer month,

		@NotNull(message = "The date field is required")
		@Min(value = 1, message = "The date in the pre order schedule field can be between 1 and 31")
		@Max(value = 31, message = "The date in the pre order schedule field can be between 1 and 31")
		Integer date,

		@NotNull(message = "The hour field is required")
		@Min(value = 0, message = "The hour in the pre order schedule field can be between 0 and 23")
		@Max(value = 23, message = "The hour in the pre order schedule field can be between 0 and 23")
		Integer hour,

		@NotNull(message = "The minute field is required")
		@Min(value = 0, message = "The month in the pre order schedule field can be between 0 and 59")
		@Max(value = 59, message = "The month in the pre order schedule field can be between 0 and 59")
		Integer minute
	) {
	}

	public enum ItemTypeRequest {
		PRE_ORDER,
		NORMAL_ORDER;

		@JsonCreator
		public static ItemTypeRequest fromString(String value) {
			try {
				return ItemTypeRequest.valueOf(value.toUpperCase());
			} catch (Exception exception) {
				throw new IllegalArgumentException("Invalid item type field: " + value);
			}
		}

		public ItemType toDto() {
			return switch (this) {
				case PRE_ORDER -> ItemType.PRE_ORDER;
				default -> ItemType.NORMAL_ORDER;
			};
		}
	}
}
