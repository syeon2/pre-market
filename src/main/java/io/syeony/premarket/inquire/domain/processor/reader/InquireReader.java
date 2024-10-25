package io.syeony.premarket.inquire.domain.processor.reader;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.syeony.premarket.inquire.domain.model.Inquire;

public interface InquireReader {

	List<Inquire> findInquiresForToday(Long itemNo, String writerId);

	Optional<Inquire> findByItemNo(Long itemNo);

	Page<Inquire> findItemInquires(Long itemNo, Pageable pageable);

}
