package io.syeony.premarket.member.infrastructure.persistence.entity;

import io.syeony.premarket.member.domain.model.vo.MemberRole;
import io.syeony.premarket.support.common.BaseEntity;
import io.syeony.premarket.support.common.EntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint", nullable = false)
	private Long id;

	@Column(name = "member_id", columnDefinition = "varchar(60)", nullable = false, unique = true)
	private String memberId;

	@Column(name = "email", columnDefinition = "varchar(60)", nullable = false, unique = true)
	private String email;

	@Column(name = "password", columnDefinition = "varchar(60)", nullable = false)
	private String password;

	@Column(name = "name", columnDefinition = "varchar(30)", nullable = false)
	private String name;

	@Column(name = "phone_number", columnDefinition = "varchar(30)", nullable = false, unique = true)
	private String phoneNumber;

	@Column(name = "base_address", columnDefinition = "varchar(255)", nullable = false)
	private String baseAddress;

	@Column(name = "address_detail", columnDefinition = "varchar(255)", nullable = false)
	private String addressDetail;

	@Column(name = "zipcode", columnDefinition = "varchar(30)", nullable = false)
	private String zipcode;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "role", columnDefinition = "varchar(30)", nullable = false)
	private MemberRole role;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(30)", nullable = false)
	private EntityStatus status;

	@Builder
	private MemberEntity(Long id, String memberId, String email, String password, String name, String phoneNumber,
		String baseAddress, String addressDetail, String zipcode, MemberRole role, EntityStatus status) {
		this.id = id;
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.baseAddress = baseAddress;
		this.addressDetail = addressDetail;
		this.zipcode = zipcode;
		this.role = role;
		this.status = status;
	}
}
