package com.niit.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "newuser_s180250")
@Component
public class User {
	@Id
	private String email;
	@Column(nullable = false)
	/* @NotEmpty(message="password must contain 8 digits(*)") */
	private String password;
	@Column(nullable = false)
	/*
	 * @NotEmpty(message=
	 * "please enter your valid firstname as mentioned in your passport(*)")
	 */
	private String firstname;
	/*
	 * @NotEmpty(message=
	 * "please enter your valid secondname as mentioned in your passport(*)")
	 */
	private String lastname;
	private String username;
	/* @Min(value=10,message="enter 10 digit phone number") */
	private String phonenumber;
	private String role;
	@Column(name = "online_status")
	private boolean online;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

}
