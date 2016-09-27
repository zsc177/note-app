package com.sdo.borachok.noteapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.sdo.borachok.noteapp.security.exception.JwtTokenMissingException;
import com.sdo.borachok.noteapp.security.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	private static final String NO_JWT_TOKEN = "No JWT token found in request headers";
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	private static final int BARER_SIZE = 7;

	public JwtAuthenticationTokenFilter() {
		super("/**");
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String header = request.getHeader(AUTHORIZATION);

		if (header == null || !header.startsWith(BEARER)) {
			throw new JwtTokenMissingException(NO_JWT_TOKEN);
		}

		String authToken = header.substring(BARER_SIZE);

		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authResult);

		chain.doFilter(request, response);
	}
}
