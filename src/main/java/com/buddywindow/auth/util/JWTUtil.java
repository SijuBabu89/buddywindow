package com.buddywindow.auth.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.buddywindow.auth.entity.TokenUserProfile;
import com.buddywindow.auth.entity.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtil {

	  //private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	  @Value("${buddywindow.app.jwtSecret}")
	  private String jwtSecret;

	  @Value("${buddywindow.app.jwtExpirationMs}")
	  private int jwtExpirationMs;

//	  public String generateJwtToken(UserDetailsImpl userPrincipal) {
//	    return generateTokenFromUsername(userPrincipal.getUsername());
//	  }
	  
		public String generateJwtToken(User user, TokenUserProfile profiles) {
			return generateTokenFromUsername(user.getUsername(), profiles);
		}

	  public String generateTokenFromUsername(String username, TokenUserProfile profiles) {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put(PrivateClaims.PROFILE.key, JsonUtil.serialize(profiles));
			claims.put(PrivateClaims.IDENTITY.key, "UUID-01");
			claims.put(PrivateClaims.UID.key, "UUID-02");
//					
//					Map.of(
//	                PrivateClaims.PROFILE.key, JsonUtil.serialize(profiles),
//	                PrivateClaims.IDENTITY.key, "UUID-01",
//	                PrivateClaims.UID.key, "UUID-02");
			System.out.println("Username : "+username);
			  System.out.println(claims);
//			  return Jwts.builder().setSubject(username).setIssuedAt(new Date())
//				        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
//				        .compact();
		  return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(username)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs * 1000))
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	  }

	  public String getUserNameFromJwtToken(String token) {
	    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	  }

	  public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (SignatureException e) {
//	      logger.error("Invalid JWT signature: {}", e.getMessage());
	    } catch (MalformedJwtException e) {
//	      logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
//	      logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
//	      logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
//	      logger.error("JWT claims string is empty: {}", e.getMessage());
	    }

	    return false;
	  }
	  
	  private enum PrivateClaims {
	        PROFILE,
	        IDENTITY,
	        UID;
	        String key;

	        PrivateClaims() {
	            this.key = this.name().toLowerCase();
	        }
	    }
}
