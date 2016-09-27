package com.sdo.borachok.noteapp.initializer;

import java.time.LocalDate;
import java.time.Month;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.repository.NoteRepository;
import com.sdo.borachok.noteapp.repository.PersonRepository;

@Component
public class TestDataInitializer {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private NoteRepository noteRepository;

	@PostConstruct
	public void init() {

		Person person1 = new Person();
		person1.setEmail("person1@gmail.com");
		person1.setPassword("person1pass");
		person1.setRole("admin");
		personRepository.save(person1);

		Person person2 = new Person();
		person2.setEmail("person2@gmail.com");
		person2.setPassword("person2pass");
		person2.setRole("user");
		personRepository.save(person2);

		Person person3 = new Person();
		person3.setEmail("person3@gmail.com");
		person3.setPassword("person3pass");
		person3.setRole("user");
		personRepository.save(person3);

		Note note1 = new Note();
		note1.setDate(LocalDate.of(2016, Month.JULY, 12));
		note1.setTitle("note1");
		note1.setText("note1 text");
		note1.setAuthor(person1);
		noteRepository.save(note1);

		Note note2 = new Note();
		note2.setDate(LocalDate.of(2016, Month.AUGUST, 7));
		note2.setTitle("note2");
		note2.setText("note2 text");
		note2.setAuthor(person1);
		noteRepository.save(note2);

		Note note3 = new Note();
		note3.setDate(LocalDate.of(2016, Month.AUGUST, 13));
		note3.setTitle("note3");
		note3.setText("note3 text");
		note3.setAuthor(person2);
		noteRepository.save(note3);

		Note note4 = new Note();
		note4.setDate(LocalDate.of(2014, Month.AUGUST, 21));
		note4.setTitle("note4");
		note4.setText("note4 text");
		note4.setAuthor(person3);
		noteRepository.save(note4);

		Note note5 = new Note();
		note5.setDate(LocalDate.of(2016, Month.APRIL, 07));
		note5.setTitle("note5");
		note5.setText("note5 text");
		note5.setAuthor(person3);
		noteRepository.save(note5);
	}

}
