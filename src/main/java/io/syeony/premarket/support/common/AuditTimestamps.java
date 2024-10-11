package io.syeony.premarket.support.common;

import java.time.LocalDateTime;

public record AuditTimestamps(
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
