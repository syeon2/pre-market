package io.syeony.premarket.item.infrastructure.persistence.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.syeony.premarket.account.domain.model.vo.MemberId;
import io.syeony.premarket.account.infrastructure.persistence.entity.QMemberEntity;
import io.syeony.premarket.item.domain.model.Category;
import io.syeony.premarket.item.domain.model.Cost;
import io.syeony.premarket.item.domain.model.Item;
import io.syeony.premarket.item.domain.model.Seller;
import io.syeony.premarket.item.infrastructure.persistence.entity.QCategoryEntity;
import io.syeony.premarket.item.infrastructure.persistence.entity.QItemEntity;
import io.syeony.premarket.support.common.AuditTimestamps;
import io.syeony.premarket.support.common.EntityStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Item> findItemsByMemberId(String memberId, Pageable pageable) {
		JPAQuery<Item> contentQuery = queryFactory.select(Projections.fields(Item.class,
				QItemEntity.itemEntity.id,
				QItemEntity.itemEntity.name,
				Projections.fields(Cost.class,
					QItemEntity.itemEntity.price,
					QItemEntity.itemEntity.discount
				).as("cost"),
				QItemEntity.itemEntity.type.as("itemType"),
				QItemEntity.itemEntity.preOrderSchedule,
				QItemEntity.itemEntity.stock,
				Projections.fields(AuditTimestamps.class,
					QItemEntity.itemEntity.createdAt
				).as("auditTimestamps"),
				QItemEntity.itemEntity.createdAt,
				Projections.fields(Category.class,
					QCategoryEntity.categoryEntity.id,
					QCategoryEntity.categoryEntity.name
				).as("category"),
				Projections.fields(MemberId.class,
					QItemEntity.itemEntity.memberId.as("value")
				).as("memberId")
			)).from(QItemEntity.itemEntity)
			.leftJoin(QCategoryEntity.categoryEntity)
			.on(QItemEntity.itemEntity.categoryId.eq(QCategoryEntity.categoryEntity.id))
			.where(
				QItemEntity.itemEntity.memberId.eq(memberId),
				QItemEntity.itemEntity.status.eq(EntityStatus.ALIVE)
			).offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		pageable.getSort().stream().forEach(sort -> {
			Order order = sort.isAscending() ? Order.ASC : Order.DESC;
			String property = sort.getProperty().equals("name") ? "name" : "createdAt";

			Path<Object> target = Expressions.path(Object.class, QItemEntity.itemEntity, property);
			OrderSpecifier<?> orderSpecifier = new OrderSpecifier(order, target);
			contentQuery.orderBy(orderSpecifier);
		});

		Long totalItemCount = queryFactory.select(QItemEntity.itemEntity.count())
			.from(QItemEntity.itemEntity)
			.where(
				QItemEntity.itemEntity.memberId.eq(memberId),
				QItemEntity.itemEntity.status.eq(EntityStatus.ALIVE)
			)
			.fetchOne();

		return new PageImpl<>(contentQuery.fetch(), pageable, totalItemCount);
	}

	@Override
	public Page<Item> findItemsByCategoryId(Long categoryId, Pageable pageable) {
		JPAQuery<Item> contentQuery = queryFactory.select(Projections.fields(Item.class,
				QItemEntity.itemEntity.id,
				QItemEntity.itemEntity.name,
				Projections.fields(Cost.class,
					QItemEntity.itemEntity.price,
					QItemEntity.itemEntity.discount
				).as("cost"),
				QItemEntity.itemEntity.stock,
				QItemEntity.itemEntity.preOrderSchedule,
				QItemEntity.itemEntity.type,
				Projections.fields(Seller.class,
					QMemberEntity.memberEntity.memberId,
					QMemberEntity.memberEntity.name
				).as("seller")
			)).from(QItemEntity.itemEntity)
			.leftJoin(QMemberEntity.memberEntity)
			.on(QItemEntity.itemEntity.memberId.eq(QMemberEntity.memberEntity.memberId))
			.where(
				QItemEntity.itemEntity.categoryId.eq(categoryId),
				QItemEntity.itemEntity.status.eq(EntityStatus.ALIVE)
			).offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		Long totalItemCount = queryFactory.select(QItemEntity.itemEntity.count())
			.from(QItemEntity.itemEntity)
			.where(
				QItemEntity.itemEntity.categoryId.eq(categoryId),
				QItemEntity.itemEntity.status.eq(EntityStatus.ALIVE)
			)
			.fetchOne();

		return new PageImpl<>(contentQuery.fetch(), pageable, totalItemCount);
	}

	@Override
	public Item findItemDetailByItemId(Long itemId) {
		return queryFactory.select(Projections.fields(Item.class,
				QItemEntity.itemEntity.id,
				QItemEntity.itemEntity.name,
				Projections.fields(Cost.class,
					QItemEntity.itemEntity.price,
					QItemEntity.itemEntity.discount
				).as("cost"),
				QItemEntity.itemEntity.introduction,
				QItemEntity.itemEntity.preOrderSchedule,
				Projections.fields(AuditTimestamps.class,
					QItemEntity.itemEntity.createdAt
				).as("auditTimestamps"),
				Projections.fields(Seller.class,
					QMemberEntity.memberEntity.memberId,
					QMemberEntity.memberEntity.name
				).as("seller")
			)).from(QItemEntity.itemEntity)
			.leftJoin(QMemberEntity.memberEntity)
			.on(QItemEntity.itemEntity.memberId.eq(QMemberEntity.memberEntity.memberId))
			.where(
				QItemEntity.itemEntity.id.eq(itemId),
				QItemEntity.itemEntity.status.eq(EntityStatus.ALIVE)
			).fetchOne();
	}

}
