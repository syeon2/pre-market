package io.syeony.premarket.inquire.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.processor.CreateInquireProcessor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquireFacade {

	private final CreateInquireProcessor createInquireProcessor;

	@Transactional
	public void createInquire(final Inquire inquire) {
		createInquireProcessor.createInquire(inquire);
	}
}
