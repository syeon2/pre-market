package io.syeony.premarket.item.persentation.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.ItemType;

public record RetrieveRegisteredItemResponse(
	@JsonProperty(value = "item_no")
	Long itemNo,

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

	@JsonProperty(value = "category")
	CategoryResponse category
) {
	public static Page<RetrieveRegisteredItemResponse> from(Page<Item> items) {
		List<RetrieveRegisteredItemResponse> response = items.getContent().stream()
			.map(domain -> new RetrieveRegisteredItemResponse(
				domain.getId(),
				domain.getName(),
				new CostResponse(domain.getCost().getPrice(), domain.getCost().getDiscount()),
				ItemTypeResponse.from(domain.getItemType()),
				new PreOrderScheduleResponse(
					domain.getPreOrderSchedule().getYear(),
					domain.getPreOrderSchedule().getMonthValue(),
					domain.getPreOrderSchedule().getDayOfMonth(),
					domain.getPreOrderSchedule().getHour(),
					domain.getPreOrderSchedule().getMinute()
				),
				domain.getStock(),
				domain.getAuditTimestamps().getCreatedAt(),
				new CategoryResponse(
					domain.getCategory().getId(),
					domain.getCategory().getName())
			))
			.toList();

		return new PageImpl<>(response, items.getPageable(), items.getTotalPages());
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
}
