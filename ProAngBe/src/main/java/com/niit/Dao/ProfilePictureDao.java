package com.niit.Dao;

import org.springframework.stereotype.Repository;

import com.niit.Model.ProfilePicture;

@Repository("profilepictureDao")
public interface ProfilePictureDao {
	void uploadProfilePicture(ProfilePicture profilePicture);

	ProfilePicture getProfilePicture(String email);

}
