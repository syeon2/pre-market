package io.syeony.premarket.item.domain.processor.reader;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.syeony.premarket.item.domain.model.Item;

public interface ItemReader {

	Optional<Item> findByItemId(String itemId);

	Page<Item> findItemsByMemberId(String memberId, Pageable pageable);

	Page<Item> findItemsByCategoryNo(Long categoryId, Pageable pageable);

	Item retrieveItemDetailByItemNo(Long itemNo);

	List<Item> findItemsByNos(List<Long> itemNos);

	Optional<Item> findItemByNo(Long itemNo);
}
