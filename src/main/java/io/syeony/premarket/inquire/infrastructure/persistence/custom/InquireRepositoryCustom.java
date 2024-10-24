package io.syeony.premarket.inquire.infrastructure.persistence.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;

public interface InquireRepositoryCustom {

	List<InquireEntity> findItemsForToday(Long itemNo, String memberId);

	Page<Inquire> findInquiresByItemNo(Long itemNo, Pageable pageable);
}
