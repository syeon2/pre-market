package io.syeony.premarket.item.persentation.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;

public record RetrieveCategoryItemResponse(
	@JsonProperty(value = "item_no")
	Long itemNo,

	@JsonProperty(value = "item_name")
	String name,

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
	public static Page<RetrieveCategoryItemResponse> from(Page<Item> domains) {
		List<RetrieveCategoryItemResponse> items = domains.getContent().stream()
			.map(item -> new RetrieveCategoryItemResponse(
				item.getId(),
				item.getName(),
				new CostResponse(item.getCost().getPrice(), item.getCost().getDiscount()),
				item.getItemType() != ItemType.NORMAL_ORDER,
				item.getStock(),
				new PreOrderScheduleResponse(
					item.getPreOrderSchedule().getYear(),
					item.getPreOrderSchedule().getMonthValue(),
					item.getPreOrderSchedule().getDayOfMonth(),
					item.getPreOrderSchedule().getHour(),
					item.getPreOrderSchedule().getMinute()
				),
				new SellerResponse(
					item.getSeller().getMemberId(),
					item.getSeller().getName()
				)
			))
			.toList();

		return new PageImpl<>(items, domains.getPageable(), domains.getTotalElements());
	}
}
