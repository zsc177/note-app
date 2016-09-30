package com.sdo.borachok.noteapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdo.borachok.noteapp.security.model.AuthenticatedUser;
import com.sdo.borachok.noteapp.service.NoteService;
import com.sdo.borachok.noteapp.service.SecurityService;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private NoteService noteService;

	@Override
	public boolean canAccessNote(AuthenticatedUser activeUser, Integer noteId) {

		return noteService.getById(noteId).getAuthor().getId().equals(activeUser.getId());

	}
}