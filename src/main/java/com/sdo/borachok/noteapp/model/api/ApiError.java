package com.sdo.borachok.noteapp.model.api;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;

	private String message;

	private Instant timestamp;

	public ApiError(HttpStatus status, String message, Instant timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

}
