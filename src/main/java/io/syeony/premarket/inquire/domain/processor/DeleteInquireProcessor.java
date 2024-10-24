package io.syeony.premarket.inquire.domain.processor;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.processor.reader.InquireReader;
import io.syeony.premarket.inquire.domain.processor.writer.InquireWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteInquireProcessor {

	private final InquireReader inquireReader;
	private final InquireWriter inquireWriter;

	public void deleteInquire(Long inquireNo, Long itemNo, String memberId) {
		validateInquire(inquireNo, itemNo, memberId);
		inquireWriter.delete(inquireNo);
	}

	private void validateInquire(Long inquireNo, Long itemNo, String memberId) {
		Inquire inquire = inquireReader.findByItemNo(inquireNo)
			.orElseThrow(() -> new IllegalArgumentException("Not found inquire"));

		if (!inquire.validate(itemNo, memberId)) {
			throw new IllegalArgumentException("Invalid item no or member id");
		}
	}
}
