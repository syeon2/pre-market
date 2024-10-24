package io.syeony.premarket.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.order.infrastructure.persistence.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
