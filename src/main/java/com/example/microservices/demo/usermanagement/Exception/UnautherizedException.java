package com.example.microservices.demo.usermanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
public class UnautherizedException extends RuntimeException {
	public UnautherizedException(String message) {
		super(message);
	}
}