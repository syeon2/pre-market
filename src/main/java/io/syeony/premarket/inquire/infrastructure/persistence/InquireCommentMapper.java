package io.syeony.premarket.inquire.infrastructure.persistence;

import org.springframework.stereotype.Component;

import io.syeony.premarket.inquire.domain.model.InquireComment;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireCommentEntity;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;

@Component
public final class InquireCommentMapper {

	public InquireCommentEntity toEntity(InquireEntity inquire, InquireComment comment) {
		return InquireCommentEntity.builder()
			.inquire(inquire)
			.message(comment.getMessage())
			.build();
	}
}
