package io.syeony.premarket.item.domain.processor;

import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.processor.repository.ItemRepository;
import io.syeony.premarket.support.error.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterItemProcessor {

	private final ItemRepository itemRepository;
	private final AccountReader accountReader;

	public Long registerItem(Item item) {
		if (!accountReader.existsByMemberId(item.getMemberId().value())) {
			throw new InvalidCredentialsException("Invalid member id");
		}
		return itemRepository.registerItem(item.initializeForRegister());
	}
}
