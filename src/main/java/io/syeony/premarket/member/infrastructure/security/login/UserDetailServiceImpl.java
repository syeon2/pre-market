package io.syeony.premarket.member.infrastructure.security.login;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.member.infrastructure.persistence.MemberRepository;
import io.syeony.premarket.member.infrastructure.persistence.entity.MemberEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberEntity member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException(email));

		return new User(
			member.getEmail(), member.getPassword(),
			true, true, true, true, new ArrayList<>());
	}
}
