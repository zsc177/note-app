package com.sdo.borachok.noteapp.security.transfer;

public class JwtUserDto {

	private Integer id;

	private String email;

	private String password;

	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "JwtUserDto [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
}
