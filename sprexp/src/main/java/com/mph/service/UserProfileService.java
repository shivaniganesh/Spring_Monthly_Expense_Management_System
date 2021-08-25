package com.mph.service;

import java.util.List;

import com.mph.entity.UserProfile;

public interface UserProfileService {

	public void addUserProfile(UserProfile userProfile);

	public List<UserProfile> updateUserProfile(UserProfile userProfile);

	public List<UserProfile> deleteUserProfile(int userId);

	public List<UserProfile> getAllUserProfile();

	// public UserProfile getUserProfile(UserProfile userProfile);

	// public List<UserProfile> getUserProfileById(int userId);
	public UserProfile login(String email, String password);
	public UserProfile findByEmail(String email);
	public UserProfile resetPassword(String email,String password);
}
