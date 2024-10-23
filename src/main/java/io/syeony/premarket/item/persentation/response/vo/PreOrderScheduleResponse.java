package io.syeony.premarket.item.persentation.response.vo;

import java.time.LocalDateTime;

public record PreOrderScheduleResponse(
	Integer year,
	Integer month,
	Integer date,
	Integer hour,
	Integer minute
) {
	public static PreOrderScheduleResponse from(LocalDateTime schedule) {
		return new PreOrderScheduleResponse(
			schedule.getYear(),
			schedule.getMonthValue(),
			schedule.getDayOfMonth(),
			schedule.getHour(),
			schedule.getMinute()
		);
	}
}
