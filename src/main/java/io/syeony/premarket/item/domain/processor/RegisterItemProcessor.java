package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.writer.ItemWriter;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterItemProcessor {

	private final ItemWriter itemWriter;
	private final AccountReader accountReader;

	public Long registerItem(Item item) {
		if (!accountReader.existsByMemberId(item.getSeller().getMemberId())) {
			throw new InvalidCredentialsException("Invalid member id");
		}
		return itemWriter.register(item.initializeForRegister());
	}
}
