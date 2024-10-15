package io.syeony.premarket.support.security.login;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.processor.reader.AccountReader;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginAccountService implements UserDetailsService {

	private final AccountReader accountReader;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountReader.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException(email));

		return new User(
			account.getEmail(), account.getPassword().encryptPassword(),
			true, true, true, true, new ArrayList<>());
	}
}
