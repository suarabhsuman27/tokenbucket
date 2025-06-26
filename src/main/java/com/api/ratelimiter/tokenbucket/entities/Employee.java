package com.api.ratelimiter.tokenbucket.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "emp_id", nullable = false, unique = true)
	private String empId;
	
	@Column(name = "name", nullable = false, unique = false)
	private String name;
	
	@Column(name = "address", nullable = false, unique = false)
	private String address;
	
	@Column(name = "salary", nullable = false, unique = false)
	private int salary;
	
}
