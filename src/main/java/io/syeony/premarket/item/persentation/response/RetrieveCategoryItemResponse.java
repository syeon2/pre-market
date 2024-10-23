package io.syeony.premarket.item.persentation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.persentation.response.vo.CostResponse;
import io.syeony.premarket.item.persentation.response.vo.PreOrderScheduleResponse;
import io.syeony.premarket.item.persentation.response.vo.SellerResponse;

public record RetrieveCategoryItemResponse(
	@JsonProperty(value = "item_no")
	Long itemNo,

	@JsonProperty(value = "item_name")
	String itemName,

	@JsonProperty(value = "cost")
	CostResponse cost,

	@JsonProperty(value = "is_pre_order_item")
	Boolean isPreOrderItem,

	Integer stock,

	@JsonProperty(value = "pre_order_schedule")
	PreOrderScheduleResponse preOrderSchedule,

	@JsonProperty(value = "seller")
	SellerResponse seller
) {
	public static RetrieveCategoryItemResponse from(Item item) {
		return new RetrieveCategoryItemResponse(
			item.getItemNo(),
			item.getItemName(),
			CostResponse.from(item.getCost()),
			item.isPreOrderType(),
			item.getStock(),
			convertToPreOrderSchedule(item.getPreOrderSchedule()),
			SellerResponse.from(item.getSeller())
		);
	}

	private static PreOrderScheduleResponse convertToPreOrderSchedule(LocalDateTime schedule) {
		return schedule == null ? null : PreOrderScheduleResponse.from(schedule);
	}
}
