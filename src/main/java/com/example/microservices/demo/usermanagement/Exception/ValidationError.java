package com.example.microservices.demo.usermanagement.Exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ValidationError {

//	private HttpStatus status;
//	private String message;
//	 private List<String> errors;
//	public ValidationError(HttpStatus status, String message, List<String> errors) {
//		super();
//		this.status = status;
//		this.message = message;
//		this.errors = errors;
//	}
	public ValidationError() {
		super();
	}
//
//	  
	 private int status;
     private String error;
     private String message;
     private List<String> detailedMessages;
	public ValidationError(int status, String error, String message, List<String> detailedMessages) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.detailedMessages = detailedMessages;
	}

    


	 
	}

