package io.syeony.premarket.inquire.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import io.syeony.premarket.inquire.infrastructure.persistence.custom.InquireRepositoryCustom;
import io.syeony.premarket.inquire.infrastructure.persistence.entity.InquireEntity;

public interface InquireRepository extends JpaRepository<InquireEntity, Long>, InquireRepositoryCustom {
}
