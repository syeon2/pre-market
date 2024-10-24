package io.syeony.premarket.inquire.domain.processor;

import java.util.List;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.processor.reader.InquireReader;
import io.syeony.premarket.inquire.domain.processor.writer.InquireWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateInquireProcessor {

	private final InquireWriter inquireWriter;
	private final InquireReader inquireReader;

	public void createInquire(final Inquire inquire) {
		List<Inquire> findItems = inquireReader.findInquiresForToday(inquire.getInquiredItem().getItemNo(),
			inquire.getWriter().getMemberId());
		if (findItems.size() >= 3) {
			throw new IllegalArgumentException("The number of inquiries allowed for today has been exceeded.");
		}

		inquireWriter.create(inquire);
	}
}
