package io.syeony.premarket.support.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String TIMEZONE = "Asia/Seoul";

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIMESTAMP_FORMAT, timezone = TIMEZONE)
	@Column(name = "created_at", columnDefinition = "timestamp", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIMESTAMP_FORMAT, timezone = TIMEZONE)
	@Column(name = "updated_at", columnDefinition = "timestamp", nullable = false)
	private LocalDateTime updatedAt;
}
