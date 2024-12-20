package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.item.domain.ItemStockHandler;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.member.domain.processor.reader.MemberReader;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterItemProcessor {

	private final ItemWriter itemWriter;
	private final ItemStockHandler itemStockHandler;
	private final MemberReader memberReader;

	public Long register(Item item) {
		if (!item.validateItem()) {
			throw new IllegalArgumentException("Invalid item (type and preSchedule)");
		}
		if (!memberReader.existsByMemberId(item.getSeller().getMemberId())) {
			throw new InvalidCredentialsException("Invalid member id");
		}

		Long register = itemWriter.register(item.initializeForRegister());
		itemStockHandler.increaseStock(register, item.getStock());

		return register;
	}
}
