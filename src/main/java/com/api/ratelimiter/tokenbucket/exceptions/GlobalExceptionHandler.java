package com.api.ratelimiter.tokenbucket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BucketTokenException.class)
	public ResponseEntity<?> getHttpErrorResponse(BucketTokenException bucketTokenException) {

		HttpStatus httpStatus = getHttpStatusCode(bucketTokenException.getErrorCode());
		return ResponseEntity.status(httpStatus).body(bucketTokenException.getErrorMessage());
	}

	private HttpStatus getHttpStatusCode(String errorCode) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		try {
			switch (errorCode) {
			case "500":
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			case "204":
				httpStatus = HttpStatus.NO_CONTENT;
				break;
			case "400":
				httpStatus = HttpStatus.BAD_REQUEST;
				break;
			case "307":
				httpStatus = HttpStatus.TEMPORARY_REDIRECT;
				break;
			case "308":
				httpStatus = HttpStatus.PERMANENT_REDIRECT;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			}
		} catch (Exception exception) {

		}
		return httpStatus;
	}
}
