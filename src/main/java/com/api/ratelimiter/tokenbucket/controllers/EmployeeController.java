package com.api.ratelimiter.tokenbucket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.ratelimiter.tokenbucket.exceptions.BucketTokenException;
import com.api.ratelimiter.tokenbucket.models.EmployeeModel;
import com.api.ratelimiter.tokenbucket.responses.ResponseMessage;
import com.api.ratelimiter.tokenbucket.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	/**
	 * API to add or updated the Employee info
	 * 
	 * @param empModel
	 * @return
	 * @throws BucketTokenException
	 */
	@RequestMapping(value = "/add-update", method = RequestMethod.PUT)
	ResponseMessage addUpdateEmployee(@RequestBody EmployeeModel empModel) throws BucketTokenException{
		
		ResponseMessage response = empService.addUpdateEmployee(empModel);
		
		return response;
	}
	
	/**
	 * API to fetch all list of available Employees 
	 * 
	 * @return
	 * @throws BucketTokenException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	List<EmployeeModel> findAll() throws BucketTokenException{
		
		List<EmployeeModel> response = empService.findAllEmployee();
		
		return response;
	}
	
	/**
	 * API to fetch employee based on given employee id
	 * 
	 * @param empId
	 * @return
	 * @throws BucketTokenException
	 */
	@RequestMapping(value = "/find/{empId}", method = RequestMethod.GET)
	EmployeeModel findByEmployeeId(@PathVariable("empId") String empId) throws BucketTokenException{
		
		EmployeeModel response = empService.findByEmployeeId(empId);
		
		return response;
	}
}
