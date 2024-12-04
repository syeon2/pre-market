package io.syeony.premarket.member.domain.processor;

import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.processor.reader.MemberReader;
import io.syeony.premarket.member.domain.processor.util.PasswordEncryptor;
import io.syeony.premarket.member.domain.processor.writer.MemberWriter;
import io.syeony.premarket.support.error.exception.DuplicatedEmailException;
import io.syeony.premarket.support.error.exception.DuplicatedPhoneNumberException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterMemberProcessor {

	private final MemberWriter memberWriter;
	private final MemberReader memberReader;
	private final PasswordEncryptor passwordEncryptor;

	public MemberId register(final Member member) {
		checkEmailIsUnique(member.getEmail());
		checkPhoneNumberIsUnique(member.getPhoneNumber());

		return memberWriter.save(
			member.registerCustomer(passwordEncryptor::encrypt));
	}

	private void checkEmailIsUnique(final String email) {
		if (memberReader.existsByEmail(email)) {
			throw new DuplicatedEmailException(email);
		}
	}

	private void checkPhoneNumberIsUnique(final String phoneNumber) {
		if (memberReader.existsByPhoneNumber(phoneNumber)) {
			throw new DuplicatedPhoneNumberException(phoneNumber);
		}
	}

	private String encryptPassword(final String rawPassword) {
		return passwordEncryptor.encrypt(rawPassword);
	}
}
