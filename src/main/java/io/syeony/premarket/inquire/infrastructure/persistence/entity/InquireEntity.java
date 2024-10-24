package io.syeony.premarket.inquire.infrastructure.persistence.entity;

import io.syeony.premarket.support.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "inquire")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InquireEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "message", columnDefinition = "varchar(255)", nullable = false)
	private String message;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false)
	private String memberId;

	@Column(name = "item_no", columnDefinition = "bigint", nullable = false)
	private Long itemNo;
}
