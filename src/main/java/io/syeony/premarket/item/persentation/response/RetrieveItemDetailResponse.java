package io.syeony.premarket.item.persentation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;

public record RetrieveItemDetailResponse(
	Long itemId,

	String name,

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
			new CostResponse(item.getCost().getPrice(), item.getCost().getDiscount()),
			item.getItemType() == ItemType.PRE_ORDER,
			item.getIntroduction(),
			new PreOrderScheduleResponse(
				item.getPreOrderSchedule().getYear(),
				item.getPreOrderSchedule().getMonthValue(),
				item.getPreOrderSchedule().getDayOfMonth(),
				item.getPreOrderSchedule().getHour(),
				item.getPreOrderSchedule().getMinute()
			),
			item.getAuditTimestamps().getCreatedAt(),
			new SellerResponse(
				item.getSeller().getMemberId(),
				item.getSeller().getName()
			)
		);
	}
}
