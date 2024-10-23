package io.syeony.premarket.item.infrastructure.persistence.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.syeony.premarket.item.domain.model.Item;

public interface ItemRepositoryCustom {

	Page<Item> findItemsByMemberId(String memberId, Pageable pageable);

	Page<Item> findItemsByCategoryId(Long categoryId, Pageable pageable);
}
