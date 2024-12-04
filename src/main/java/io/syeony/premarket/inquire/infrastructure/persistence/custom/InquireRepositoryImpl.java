package io.syeony.premarket.inquire.infrastructure.persistence.custom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.model.Writer;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.QInquireEntity;
import io.syeony.premarket.member.infrastructure.persistence.entity.QMemberEntity;
import io.syeony.premarket.support.common.AuditTimestamps;
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

	@Override
	public Page<Inquire> findInquiresByItemNo(Long itemNo, Pageable pageable) {
		JPAQuery<Inquire> contentQuery = queryFactory
			.select(Projections.fields(Inquire.class,
				QInquireEntity.inquireEntity.id.as("inquireNo"),
				QInquireEntity.inquireEntity.message,
				Projections.fields(AuditTimestamps.class,
					QInquireEntity.inquireEntity.createdAt,
					QInquireEntity.inquireEntity.updatedAt
				).as("auditTimestamps"),
				Projections.fields(Writer.class,
					QMemberEntity.memberEntity.memberId,
					QMemberEntity.memberEntity.name
				).as("writer")
			)).from(QInquireEntity.inquireEntity)
			.leftJoin(QMemberEntity.memberEntity)
			.on(QInquireEntity.inquireEntity.memberId.eq(QMemberEntity.memberEntity.memberId))
			.where(QInquireEntity.inquireEntity.itemNo.eq(itemNo))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		Long totalCount = queryFactory.select(QInquireEntity.inquireEntity.count())
			.from(QInquireEntity.inquireEntity)
			.where(QInquireEntity.inquireEntity.itemNo.eq(itemNo))
			.fetchOne();

		return new PageImpl<>(contentQuery.fetch(), pageable, totalCount);
	}
}
