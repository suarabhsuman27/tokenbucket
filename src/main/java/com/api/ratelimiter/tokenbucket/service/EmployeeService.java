package com.api.ratelimiter.tokenbucket.service;

import java.util.List;

import com.api.ratelimiter.tokenbucket.exceptions.BucketTokenException;
import com.api.ratelimiter.tokenbucket.models.EmployeeModel;
import com.api.ratelimiter.tokenbucket.responses.ResponseMessage;

public interface EmployeeService {

	ResponseMessage addUpdateEmployee(EmployeeModel employeeModel) throws BucketTokenException;
	
	List<EmployeeModel> findAllEmployee() throws BucketTokenException;
	
	EmployeeModel findByEmployeeId(String employeeId) throws BucketTokenException;
}
