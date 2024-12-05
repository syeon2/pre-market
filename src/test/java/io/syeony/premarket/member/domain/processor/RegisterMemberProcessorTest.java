package io.syeony.premarket.member.domain.processor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.syeony.premarket.UnitTestSupport;
import io.syeony.premarket.member.domain.model.Member;
import io.syeony.premarket.member.domain.model.vo.MemberId;
import io.syeony.premarket.member.domain.model.vo.Password;
import io.syeony.premarket.member.domain.processor.reader.MemberReader;
import io.syeony.premarket.member.domain.processor.util.PasswordEncryptor;
import io.syeony.premarket.member.domain.processor.writer.MemberWriter;
import io.syeony.premarket.support.error.exception.DuplicatedEmailException;
import io.syeony.premarket.support.error.exception.DuplicatedPhoneNumberException;

class RegisterMemberProcessorTest extends UnitTestSupport {

	@InjectMocks
	private RegisterMemberProcessor registerMemberProcessor;

	@Mock
	private MemberWriter memberWriter = mock(MemberWriter.class);

	@Mock
	private MemberReader memberReader = mock(MemberReader.class);

	@Mock
	private PasswordEncryptor passwordEncryptor = mock(PasswordEncryptor.class);

	@BeforeEach
	void setUp() {
		registerMemberProcessor = new RegisterMemberProcessor(memberWriter, memberReader, passwordEncryptor);
	}

	@Test
	@DisplayName(value = "Should successfully register when the email and phone number are valid")
	void registerMember_shouldReturnId_whenEmailAndPhoneNumberAreValid() {
		// given
		Member member = mock(Member.class);

		when(member.getEmail()).thenReturn("waterkite94@gmail.com");
		when(member.getPhoneNumber()).thenReturn("01011112222");
		when(member.getPassword()).thenReturn(new Password("rawPassword", null));
		when(member.register(any())).thenReturn(member);
		when(memberWriter.save(member)).thenReturn(new MemberId("new-member-id"));

		// when
		MemberId result = registerMemberProcessor.register(member);

		// then
		assertNotNull(result);
		assertEquals("new-member-id", result.value());
		verify(memberReader).existsByEmail("waterkite94@gmail.com");
		verify(memberReader).existsByPhoneNumber("01011112222");
		verify(memberWriter).save(member);
	}

	@Test
	@DisplayName("Should throw DuplicatedEmailException when the email already exists")
	void register_shouldThrowDuplicatedEmailException_whenEmailAlreadyExists() {
		// given
		Member member = mock(Member.class);
		when(member.getEmail()).thenReturn("waterkite94@gmail.com");
		when(memberReader.existsByEmail("waterkite94@gmail.com")).thenReturn(true);

		// when & then
		assertThrows(DuplicatedEmailException.class, () -> {
			registerMemberProcessor.register(member);
		});

		verify(memberReader).existsByEmail("waterkite94@gmail.com");
		verify(memberReader, never()).existsByPhoneNumber(any());
		verify(memberWriter, never()).save(any());
	}

	@Test
	@DisplayName("Should throw DuplicatedPhoneNumberException when the phone number already exists")
	void register_shouldThrowDuplicatedPhoneNumberException_whenPhoneNumberAlreadyExists() {
		// given
		Member member = mock(Member.class);
		when(member.getEmail()).thenReturn("waterkite94@gmail.com");
		when(member.getPhoneNumber()).thenReturn("00011112222");
		when(memberReader.existsByPhoneNumber("00011112222")).thenReturn(true);

		// when & then
		assertThrows(DuplicatedPhoneNumberException.class, () -> {
			registerMemberProcessor.register(member);
		});

		verify(memberReader).existsByEmail("waterkite94@gmail.com");
		verify(memberReader).existsByPhoneNumber("00011112222");
		verify(memberWriter, never()).save(any());
	}

}
