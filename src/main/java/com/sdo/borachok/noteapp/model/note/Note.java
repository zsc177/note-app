package com.sdo.borachok.noteapp.model.note;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sdo.borachok.noteapp.model.person.Person;

@Entity
public class Note {

	@Id
	@GeneratedValue
	private Integer id;

	private LocalDate date;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String title;

	@Size(min = 0, max = 5000)
	private String text;

	@ManyToOne
	private Person author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", date=" + date + ", title=" + title + ", text=" + text + ", author=" + author + "]";
	}

}
