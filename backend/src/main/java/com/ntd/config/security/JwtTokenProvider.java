package com.ntd.config.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;
	
	/**
	 * jwt 발급
	 * @param authentication
	 * @return
	 */
	public String generateToken(Authentication authentication) {

		Date now = new Date();
		Date expired = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder().setSubject(String.valueOf(authentication.getPrincipal())).setIssuedAt(now)
				.setExpiration(expired).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	/**
	 * TOKEN을부터 특정 정보 추출
	 * @param token
	 * @return
	 */
	public String getUserFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}
	
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)	;
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("UnSopported JWT token {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims invalid  {}", e.getMessage());
		}
		return false;
	}
}
