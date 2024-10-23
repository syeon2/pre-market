package io.syeony.premarket.item.persentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.persentation.request.vo.CostRequest;
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

	@JsonProperty(value = "category_no")
	@NotNull(message = "The category no field is required")
	Long categoryNo,

	@JsonProperty(value = "seller_id")
	@NotBlank(message = "The seller id is required")
	String sellerId
) {
	public Item toDomain() {
		return Item.builder()
			.itemName(name)
			.cost(cost.toDomain())
			.stock(stock)
			.introduction(introduction)
			.category(Category.initNo(categoryNo))
			.seller(Seller.initId(sellerId))
			.build();
	}
}
