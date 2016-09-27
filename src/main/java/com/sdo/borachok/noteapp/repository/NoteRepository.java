package com.sdo.borachok.noteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdo.borachok.noteapp.model.note.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	List<Note> findByAuthorId(Integer id);

}