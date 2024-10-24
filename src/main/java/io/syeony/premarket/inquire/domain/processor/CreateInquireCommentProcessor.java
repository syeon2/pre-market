package io.syeony.premarket.inquire.domain.processor;

import io.syeony.premarket.inquire.domain.model.InquireComment;
import io.syeony.premarket.inquire.domain.processor.writer.InquireWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateInquireCommentProcessor {

	private final InquireWriter inquireWriter;

	public void createInquireComment(InquireComment comment) {
		inquireWriter.createComment(comment);
	}
}
