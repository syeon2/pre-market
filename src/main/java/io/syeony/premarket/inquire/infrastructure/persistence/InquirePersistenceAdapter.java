package io.syeony.premarket.inquire.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.inquire.domain.model.Inquire;
import io.syeony.premarket.inquire.domain.model.InquireComment;
import io.syeony.premarket.inquire.domain.processor.reader.InquireReader;
import io.syeony.premarket.inquire.domain.processor.writer.InquireWriter;
import io.syeony.premarket.inquire.infrastructure.InquireMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InquirePersistenceAdapter implements InquireReader, InquireWriter {

	private final InquireRepository inquireRepository;
	private final InquireMapper inquireMapper;
	private final InquireCommentMapper inquireCommentMapper;

	@Override
	public void create(final Inquire inquire) {
		inquireRepository.save(inquireMapper.toEntity(inquire));
	}

	@Override
	public void delete(Long inquireNo) {
		inquireRepository.deleteById(inquireNo);
	}

	@Override
	public void createComment(InquireComment comment) {
		inquireRepository.findById(comment.getInquireNo()).ifPresent(entity -> {
			entity.addInquireComment(inquireCommentMapper.toEntity(entity, comment));
		});
	}

	@Override
	public void deleteComment(Long inquireNo, Long commentNo) {
		inquireRepository.findById(inquireNo).ifPresent(entity -> {
			entity.deleteInquireComment(commentNo);
		});
	}

	@Override
	public List<Inquire> findInquiresForToday(final Long itemNo, final String writerId) {
		return inquireRepository.findItemsForToday(itemNo, writerId).stream()
			.map(inquireMapper::toDomain)
			.toList();
	}

	@Override
	public Optional<Inquire> findByItemNo(Long inquireNo) {
		return inquireRepository.findById(inquireNo)
			.map(inquireMapper::toDomain);
	}

	@Override
	public Page<Inquire> findItemInquires(Long itemNo, Pageable pageable) {
		return inquireRepository.findInquiresByItemNo(itemNo, pageable);
	}

	@Override
	public Optional<InquireComment> findCommentByInquireNo(Long inquireNo) {
		return Optional.empty();
	}
}
