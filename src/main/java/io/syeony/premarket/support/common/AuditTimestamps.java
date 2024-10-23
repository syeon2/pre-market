package io.syeony.premarket.support.common;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuditTimestamps {
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public AuditTimestamps(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
