package com.api.ratelimiter.tokenbucket.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BucketTokenException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	private String errorMessage;

}
