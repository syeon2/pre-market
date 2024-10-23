package io.syeony.premarket.item.persentation.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.persentation.request.vo.CostRequest;
import io.syeony.premarket.item.persentation.request.vo.ItemTypeRequest;
import io.syeony.premarket.item.persentation.request.vo.PreOrderScheduleRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterItemRequest(

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

	@JsonProperty(value = "item_type")
	@NotNull(message = "The item type field is required")
	ItemTypeRequest itemType,

	@Valid
	@Nullable
	@JsonProperty(value = "pre_order_schedule")
	PreOrderScheduleRequest preOrderSchedule,

	@JsonProperty(value = "seller_id")
	@NotBlank(message = "The seller id is required")
	String sellerId,

	@JsonProperty(value = "category_no")
	@NotNull(message = "The category no field is required")
	Long categoryNo
) {
	public Item toDomain() {
		return Item.builder()
			.name(name)
			.cost(cost.toDomain())
			.stock(stock)
			.introduction(introduction)
			.itemType(itemType.toDomain())
			.preOrderSchedule(convertToPreOrderSchedule())
			.seller(Seller.initId(sellerId))
			.category(Category.initNo(categoryNo))
			.build();
	}

	private LocalDateTime convertToPreOrderSchedule() {
		return preOrderSchedule == null ? null : preOrderSchedule.toLocalDateTime();
	}
}
