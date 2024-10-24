package io.syeony.premarket.inquire.domain.processor;

import io.syeony.premarket.inquire.domain.processor.writer.InquireWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteInquireCommentProcessor {

	private final InquireWriter inquireWriter;

	public void deleteInquireComment(Long inquireNo, Long commentNo) {
		inquireWriter.deleteComment(inquireNo, commentNo);
	}
}
