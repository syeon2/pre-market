package io.syeony.premarket.inquire.domain.processor.writer;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.model.InquireComment;

public interface InquireWriter {

	void create(Inquire inquire);

	void delete(Long inquireNo);

	void createComment(InquireComment comment);

	void deleteComment(Long inquireNo, Long commentNo);
}
