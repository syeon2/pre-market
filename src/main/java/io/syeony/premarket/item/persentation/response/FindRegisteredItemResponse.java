package io.syeony.premarket.item.persentation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.persentation.response.vo.CategoryResponse;
import io.syeony.premarket.item.persentation.response.vo.CostResponse;
import io.syeony.premarket.item.persentation.response.vo.ItemTypeResponse;
import io.syeony.premarket.item.persentation.response.vo.PreOrderScheduleResponse;

public record FindRegisteredItemResponse(
	@JsonProperty(value = "item_no")
	Long itemNo,

	@JsonProperty(value = "item_name")
	String itemName,

	@JsonProperty(value = "cost")
	CostResponse cost,

	@JsonProperty(value = "item_type")
	ItemTypeResponse itemType,

	@JsonProperty(value = "pre_order_schedule")
	PreOrderScheduleResponse preOrderSchedule,

	Integer stock,

	@JsonProperty(value = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDateTime createdAt,

	@JsonProperty(value = "category")
	CategoryResponse category
) {
	public static FindRegisteredItemResponse from(Item item) {
		return new FindRegisteredItemResponse(
			item.getItemNo(),
			item.getItemName(),
			CostResponse.from(item.getCost()),
			ItemTypeResponse.from(item.getItemType()),
			convertToPreOrderSchedule(item.getPreOrderSchedule()),
			item.getStock(),
			item.getAuditTimestamps().getCreatedAt(),
			CategoryResponse.from(item.getCategory())
		);
	}

	private static PreOrderScheduleResponse convertToPreOrderSchedule(LocalDateTime schedule) {
		return schedule == null ? null : PreOrderScheduleResponse.from(schedule);
	}
}
