package io.syeony.premarket.inquire.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.processor.reader.InquireReader;
import io.syeony.premarket.inquire.domain.processor.writer.InquireWriter;
import io.syeony.premarket.inquire.infrastructure.InquireMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InquirePersistenceAdapter implements InquireReader, InquireWriter {

	private final InquireRepository inquireRepository;
	private final InquireMapper inquireMapper;

	@Override
	public void create(final Inquire inquire) {
		inquireRepository.save(inquireMapper.toEntity(inquire));
	}

	@Override
	public List<Inquire> findInquiresForToday(final Long itemNo, final String writerId) {
		return inquireRepository.findItemsForToday(itemNo, writerId).stream()
			.map(inquireMapper::toDomain)
			.toList();
	}
}
