package com.buddywindow.auth.entity.domain;

import java.time.LocalDateTime;

import com.buddywindow.auth.constant.TokenType;


public class AuthToken {

	private final String accessToken;
    private final String refreshToken;
    private final TokenType tokenType;
    private final int accessTokenExpiryInSec;
    private final int refreshTokenExpiryInSec;
    private final LocalDateTime createdAt;
    
	public AuthToken(String accessToken, String refreshToken, TokenType tokenType, int accessTokenExpiryInSec,
			int refreshTokenExpiryInSec, LocalDateTime createdAt) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.tokenType = tokenType;
		this.accessTokenExpiryInSec = accessTokenExpiryInSec;
		this.refreshTokenExpiryInSec = refreshTokenExpiryInSec;
		this.createdAt = createdAt;
	}
    
    
}
