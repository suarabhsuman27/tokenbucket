package com.api.ratelimiter.tokenbucket.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {

	private UUID id;
	
	private String empId;
	
	private String name;
	
	private String address;
	
	private int salary;
}
