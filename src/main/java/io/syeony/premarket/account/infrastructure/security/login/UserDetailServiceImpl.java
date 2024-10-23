package io.syeony.premarket.account.infrastructure.security.login;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.account.infrastructure.persistence.AccountRepository;
import io.syeony.premarket.account.infrastructure.persistence.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberEntity account = accountRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException(email));

		return new User(
			account.getEmail(), account.getPassword(),
			true, true, true, true, new ArrayList<>());
	}
}
