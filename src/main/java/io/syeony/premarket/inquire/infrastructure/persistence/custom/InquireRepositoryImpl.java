package io.syeony.premarket.inquire.infrastructure.persistence.custom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.QInquireEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InquireRepositoryImpl implements InquireRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<InquireEntity> findItemsForToday(Long itemNo, String memberId) {
		LocalDate today = LocalDate.now();
		LocalDateTime startOfDat = today.atStartOfDay();
		LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

		return queryFactory
			.selectFrom(QInquireEntity.inquireEntity)
			.where(
				QInquireEntity.inquireEntity.itemNo.eq(itemNo),
				QInquireEntity.inquireEntity.memberId.eq(memberId),
				QInquireEntity.inquireEntity.createdAt.between(startOfDat, endOfDay)
			).fetch();
	}
}
