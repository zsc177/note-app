package com.sdo.borachok.noteapp.model.person;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdo.borachok.noteapp.model.note.Note;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 50)
	private String password;

	private String role;

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Note> notes;

	public Person(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Person() {
	}

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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

}
