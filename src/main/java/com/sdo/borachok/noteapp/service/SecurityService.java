package com.sdo.borachok.noteapp.service;

import com.sdo.borachok.noteapp.security.model.AuthenticatedUser;

public interface SecurityService {

	public boolean canAccessNote(AuthenticatedUser activeUser, Integer noteId);

}