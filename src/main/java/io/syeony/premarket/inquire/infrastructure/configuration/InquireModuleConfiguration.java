package io.syeony.premarket.inquire.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.syeony.premarket.inquire.domain.processor.CreateInquireCommentProcessor;
import io.syeony.premarket.inquire.domain.processor.CreateInquireProcessor;
import io.syeony.premarket.inquire.domain.processor.DeleteInquireCommentProcessor;
import io.syeony.premarket.inquire.domain.processor.DeleteInquireProcessor;

@Import({
	CreateInquireProcessor.class,
	DeleteInquireProcessor.class,
	CreateInquireCommentProcessor.class,
	DeleteInquireCommentProcessor.class,
})
@Configuration
public class InquireModuleConfiguration {
}
