package io.syeony.premarket.inquire.infrastructure.persistence.custom;

import java.util.List;

import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;

public interface InquireRepositoryCustom {

	List<InquireEntity> findItemsForToday(Long itemNo, String memberId);
}
