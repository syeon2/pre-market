package io.syeony.premarket.item.persentation.request.vo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PreOrderScheduleRequest(
	@NotNull(message = "The year field is required")
	@Min(value = 2020, message = "The year in the pre order schedule field can't be less than 2020")
	@Max(value = 2100, message = "The year in the pre order schedule field can't be more than 2100")
	Integer year,

	@NotNull(message = "The month field is required")
	@Min(value = 1, message = "The month in the pre order schedule field can be between 1 and 20")
	@Max(value = 12, message = "The month in the pre order schedule field can be between 1 and 20")
	Integer month,

	@NotNull(message = "The date field is required")
	@Min(value = 1, message = "The date in the pre order schedule field can be between 1 and 31")
	@Max(value = 31, message = "The date in the pre order schedule field can be between 1 and 31")
	Integer date,

	@NotNull(message = "The hour field is required")
	@Min(value = 0, message = "The hour in the pre order schedule field can be between 0 and 23")
	@Max(value = 23, message = "The hour in the pre order schedule field can be between 0 and 23")
	Integer hour,

	@NotNull(message = "The minute field is required")
	@Min(value = 0, message = "The month in the pre order schedule field can be between 0 and 59")
	@Max(value = 59, message = "The month in the pre order schedule field can be between 0 and 59")
	Integer minute
) {
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.of(year, month, date, hour, minute);
	}
}
