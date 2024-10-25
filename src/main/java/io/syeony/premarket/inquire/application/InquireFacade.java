package io.syeony.premarket.inquire.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.model.InquireComment;
import io.syeony.premarket.inquire.domain.processor.CreateInquireCommentProcessor;
import io.syeony.premarket.inquire.domain.processor.CreateInquireProcessor;
import io.syeony.premarket.inquire.domain.processor.DeleteInquireCommentProcessor;
import io.syeony.premarket.inquire.domain.processor.DeleteInquireProcessor;
import io.syeony.premarket.inquire.domain.processor.reader.InquireReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquireFacade {

	private final CreateInquireProcessor createInquireProcessor;
	private final DeleteInquireProcessor deleteInquireProcessor;
	private final CreateInquireCommentProcessor createInquireCommentProcessor;
	private final DeleteInquireCommentProcessor deleteInquireCommentProcessor;
	private final InquireReader inquireReader;

	@Transactional
	public void createInquire(final Inquire inquire) {
		createInquireProcessor.createInquire(inquire);
	}

	@Transactional
	public void deleteInquire(Long inquireNo, Long itemNo, String memberId) {
		deleteInquireProcessor.deleteInquire(inquireNo, itemNo, memberId);
	}

	@Transactional(readOnly = true)
	public Page<Inquire> findItemInquires(Long itemNo, Pageable pageable) {
		return inquireReader.findItemInquires(itemNo, pageable);
	}

	@Transactional
	public void createInquireComment(InquireComment domain) {
		createInquireCommentProcessor.createInquireComment(domain);
	}

	@Transactional
	public void deleteInquireComment(Long inquireNo, Long commentNo) {
		deleteInquireCommentProcessor.deleteInquireComment(inquireNo, commentNo);
	}
}
