package io.syeony.premarket.inquire.domain.processor.reader;

import java.util.List;

import io.syeony.premarket.inquire.domain.model.Inquire;

public interface InquireReader {

	List<Inquire> findInquiresForToday(Long itemNo, String writerId);
}
