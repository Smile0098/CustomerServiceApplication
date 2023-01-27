package com.bankapp.customerservice.customexception;

import java.time.LocalDate;

public class ErrorDetails {
	// timestamp
	// errromsg
	// details
	private LocalDate timeStamp;
	private String errorCode;
	private String message;
	private String details;

	public ErrorDetails(LocalDate timeStamp, String errorCode, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.errorCode = errorCode;
		this.message = message;
		this.details = details;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
