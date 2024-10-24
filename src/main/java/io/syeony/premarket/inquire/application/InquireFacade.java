package io.syeony.premarket.inquire.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.processor.CreateInquireProcessor;
import io.syeony.premarket.inquire.domain.processor.DeleteInquireProcessor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquireFacade {

	private final CreateInquireProcessor createInquireProcessor;
	private final DeleteInquireProcessor deleteInquireProcessor;

	@Transactional
	public void createInquire(final Inquire inquire) {
		createInquireProcessor.createInquire(inquire);
	}

	@Transactional
	public void deleteInquire(Long inquireNo, Long itemNo, String memberId) {
		deleteInquireProcessor.deleteInquire(inquireNo, itemNo, memberId);
	}
}
