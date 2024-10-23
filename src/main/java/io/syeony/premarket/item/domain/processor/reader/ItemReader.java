package io.syeony.premarket.item.domain.processor.reader;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.syeony.premarket.item.domain.model.Item;

public interface ItemReader {

	Optional<Item> findByItemId(String itemId);

	Page<Item> findItemsByMemberId(String memberId, Pageable pageable);

	Page<Item> findItemsByCategoryId(Long categoryId, Pageable pageable);

	Item retrieveItemDetailByItemId(Long itemId);
}
