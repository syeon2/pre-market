package io.syeony.premarket.support.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.syeony.premarket.support.security.TokenProvider;
import io.syeony.premarket.support.security.login.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final TokenProvider tokenProvider;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			LoginRequest creds = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

			return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(creds.email(), creds.password(), new ArrayList<>()));
		} catch (IOException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
		String username = ((User)authResult.getPrincipal()).getUsername();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		response.addHeader(HttpHeaders.AUTHORIZATION,
			"Bearer " + tokenProvider.generateToken(userDetails.getUsername()));
	}
}
