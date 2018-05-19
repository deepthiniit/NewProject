package com.niit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "newprofilepicture_s180250")
public class ProfilePicture {
	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	private String email;
	@Lob
	private byte[] image;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
