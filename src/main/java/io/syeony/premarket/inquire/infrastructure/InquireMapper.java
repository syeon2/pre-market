package io.syeony.premarket.inquire.infrastructure;

import org.springframework.stereotype.Component;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.model.InquiredItem;
import io.syeony.premarket.inquire.domain.model.Writer;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;

@Component
public final class InquireMapper {

	public Inquire toDomain(InquireEntity entity) {
		return Inquire.builder()
			.inquireNo(entity.getItemNo())
			.message(entity.getMessage())
			.writer(Writer.of(entity.getMemberId(), null))
			.inquiredItem(InquiredItem.of(entity.getItemNo(), null))
			.build();
	}

	public InquireEntity toEntity(Inquire domain) {
		return InquireEntity.builder()
			.id(domain.getInquireNo())
			.message(domain.getMessage())
			.memberId(domain.getWriter().getMemberId())
			.itemNo(domain.getInquiredItem().getItemNo())
			.build();
	}
}
