package com.sdo.borachok.noteapp.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sdo.borachok.noteapp.security.transfer.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenValidator {

	@Value("${jwt.secret}")
	private String secret;

	public JwtUserDto parseToken(String token) {

		JwtUserDto user = null;

		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			user = new JwtUserDto();
			user.setEmail(body.getSubject());
			user.setId(Integer.parseInt((String) body.get("userId")));
			user.setPassword((String) body.get("password"));
			user.setRole((String) body.get("role"));

		} catch (JwtException e) {
			e.printStackTrace();
		}
		return user;
	}
}
