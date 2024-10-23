package io.syeony.premarket.item.persentation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.persentation.response.vo.CostResponse;
import io.syeony.premarket.item.persentation.response.vo.PreOrderScheduleResponse;
import io.syeony.premarket.item.persentation.response.vo.SellerResponse;

public record RetrieveItemDetailResponse(
	@JsonProperty(value = "item_no")
	Long itemNo,

	@JsonProperty(value = "item_name")
	String itemName,

	@JsonProperty(value = "cost")
	CostResponse cost,

	@JsonProperty(value = "is_pre_order_item")
	Boolean isPreOrderItem,

	String introduction,

	@JsonProperty(value = "pre_order_schedule")
	PreOrderScheduleResponse preOrderSchedule,

	@JsonProperty(value = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDateTime createdAt,

	@JsonProperty(value = "seller")
	SellerResponse seller
) {
	public static RetrieveItemDetailResponse from(Item item) {
		return new RetrieveItemDetailResponse(
			item.getId(),
			item.getName(),
			CostResponse.from(item.getCost()),
			item.isPreOrderType(),
			item.getIntroduction(),
			PreOrderScheduleResponse.from(item.getPreOrderSchedule()),
			item.getAuditTimestamps().getCreatedAt(),
			SellerResponse.from(item.getSeller())
		);
	}
}
