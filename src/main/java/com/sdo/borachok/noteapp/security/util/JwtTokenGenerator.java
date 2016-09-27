package com.sdo.borachok.noteapp.security.util;

import com.sdo.borachok.noteapp.security.transfer.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenGenerator {

	public static String generateToken(JwtUserDto user, String secret) {

		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("userId", user.getId() + "");
		claims.put("password", user.getPassword());
		claims.put("role", user.getRole());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}
