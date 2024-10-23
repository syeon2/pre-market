package io.syeony.premarket.item.persentation.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.application.dto.RetrieveRegisteredItemDto;
import io.syeony.premarket.item.domain.model.ItemType;

public record RetrieveRegisteredItemResponse(
	@JsonProperty(value = "item_id")
	Long itemId,

	@JsonProperty(value = "item_name")
	String itemName,

	CostResponse cost,

	@JsonProperty(value = "item_type")
	ItemTypeResponse itemType,

	@JsonProperty(value = "pre_order_schedule")
	PreOrderScheduleResponse preOrderSchedule,

	Integer stock,

	@JsonProperty(value = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDateTime createdAt,

	@JsonProperty(value = "category_id")
	Long categoryId,

	@JsonProperty(value = "category_name")
	String categoryName
) {
	public static List<RetrieveRegisteredItemResponse> from(List<RetrieveRegisteredItemDto> items) {
		return items.stream()
			.map(dto -> new RetrieveRegisteredItemResponse(
				dto.itemId(),
				dto.itemName(),
				new CostResponse(dto.price(), dto.discount()),
				ItemTypeResponse.from(dto.itemType()),
				new PreOrderScheduleResponse(
					dto.preOrderSchedule().getYear(),
					dto.preOrderSchedule().getMonthValue(),
					dto.preOrderSchedule().getDayOfMonth(),
					dto.preOrderSchedule().getHour(),
					dto.preOrderSchedule().getMinute()
				),
				dto.stock(),
				dto.createdAt(),
				dto.categoryId(),
				dto.categoryName()
			))
			.toList();
	}

	public record CostResponse(
		Integer price,
		Integer discount
	) {
	}

	public enum ItemTypeResponse {
		PRE_ORDER,
		NORMAL_ORDER;

		public static ItemTypeResponse from(ItemType itemType) {
			return switch (itemType) {
				case PRE_ORDER -> PRE_ORDER;
				default -> NORMAL_ORDER;
			};
		}
	}

	public record PreOrderScheduleResponse(
		Integer year,
		Integer month,
		Integer date,
		Integer hour,
		Integer minute
	) {
	}
}
