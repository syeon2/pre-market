package io.syeony.premarket.inquire.domain.processor.reader;

import java.util.List;
import java.util.Optional;

import io.syeony.premarket.inquire.domain.model.Inquire;

public interface InquireReader {

	List<Inquire> findInquiresForToday(Long itemNo, String writerId);

	Optional<Inquire> findByItemNo(Long itemNo);
}
