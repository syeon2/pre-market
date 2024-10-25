package io.syeony.premarket.inquire.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import io.syeony.premarket.support.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@OneToMany(
		mappedBy = "inquire",
		cascade = CascadeType.ALL,
		orphanRemoval = true)
	private List<InquireCommentEntity> inquireComment = new ArrayList<>();

	public void addInquireComment(InquireCommentEntity inquireComment) {
		inquireComment.addComment(this);
		this.inquireComment.add(inquireComment);
	}

	public void deleteInquireComment(Long commentNo) {
		getInquireComment().removeIf(entity -> entity.getId().equals(commentNo));
	}
}
