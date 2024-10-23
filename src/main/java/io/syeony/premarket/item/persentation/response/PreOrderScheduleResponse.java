package io.syeony.premarket.item.persentation.response;

public record PreOrderScheduleResponse(
	Integer year,
	Integer month,
	Integer date,
	Integer hour,
	Integer minute
) {
}
