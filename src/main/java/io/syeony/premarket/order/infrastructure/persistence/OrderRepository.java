package io.syeony.premarket.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.order.infrastructure.persistence.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
