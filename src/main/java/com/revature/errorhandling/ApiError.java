package com.revature.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
	
	/*
	 * This class is designed to represent information about an HTTP Error.
	 * 
	 * The structure of this class can be serialized into JSON and sent
	 * back to the client about what went wrong.
	 */
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp;
	
	private int status;
	
	private String error; // represetns our HTTP error (in words)

	private String message;
	private String debugMessage;
	List<ApiSubError> subErrors = new ArrayList<>();
	
	protected ApiError() {
		super();
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiError(final HttpStatus status) {
		this();
		this.status = status.value(); 
		this.error = status.getReasonPhrase();
	}
	
	public ApiError(final HttpStatus status, final Throwable ex) {
		this(status);
		this.message = "No message available";
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	public ApiError(final HttpStatus status, final String message, final Throwable ex) {
		this(status, ex);
		this.message = message;
	}
	
	public void addSubError(final ApiSubError error) {
		
		this.subErrors.add(error);
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public String getError() {
		return this.error;
	}

	public void setError(final String error) {
		this.error = error;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return this.debugMessage;
	}

	public void setDebugMessage(final String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public List<ApiSubError> getSubErrors() {
		return this.subErrors;
	}

	public void setSubErrors(final List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	@Override
	public String toString() {
		return "ApiError [timestamp=" + this.timestamp + ", status=" + this.status + ", error=" + this.error + ", message=" + this.message
				+ ", debugMessage=" + this.debugMessage + ", subErrors=" + this.subErrors + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.debugMessage, this.error, this.message, this.status, this.subErrors, this.timestamp);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ApiError other = (ApiError) obj;
		return Objects.equals(this.debugMessage, other.debugMessage) && Objects.equals(this.error, other.error)
				&& Objects.equals(this.message, other.message) && this.status == other.status
				&& Objects.equals(this.subErrors, other.subErrors) && Objects.equals(this.timestamp, other.timestamp);
	}
	
	
	
}
