package io.syeony.premarket.account.application;

import org.springframework.stereotype.Service;

import io.syeony.premarket.account.application.dto.RegisterAccountDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public final class AccountFacade {

	public String register(RegisterAccountDto accountDto, String verificationCode) {
		return "memberId";
	}
}
