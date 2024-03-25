package com.buddywindow.auth.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.entity.TokenUserProfile;
import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.util.JWTUtil;
import com.buddywindow.auth.util.JsonUtil;



@Service
public class AuthService implements IAuthService{


	@Autowired
	private IUserService userService;
	@Autowired
	private TokenGeneratorService tokenGeneratorService;
	
	@Override
	public String getAuthToken(String username, String password) {
		User user = userService.getUserById(1l);
		TokenUserProfile tokenUserProfile = toUserProfile(user);
		String accessToken = tokenGeneratorService.generateAccessTokenFormUsername(username, tokenUserProfile);
		String refreshToken = tokenGeneratorService.generateRefreshTokenFormUsername(username, tokenUserProfile);
		return accessToken;
	}
	
    private static TokenUserProfile toUserProfile(User user) {
		return new TokenUserProfile(user.getId(), user.getUsername(), user.getContact().getEmail(), 
				user.getTitle(), user.getFirstName(), user.getMiddleName(), user.getLastName());
    }
    
    

}
